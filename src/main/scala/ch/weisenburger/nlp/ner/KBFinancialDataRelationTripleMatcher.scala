package ch.weisenburger.nlp.ner

import ch.weisenburger.crosscutting_concerns.{Instrumented, LibConfig, Logger}
import ch.weisenburger.nlp.util.NumberFormatConverter
import ch.weisenburger.sparql.{LargeQuery, SparqlQueryExecutor}
import com.hp.hpl.jena.query.QuerySolution
import com.hp.hpl.jena.rdf.model.RDFNode

import scala.concurrent.duration._

/**
 * Created by Norman on 11.05.14.
 */
object KBFinancialDataRelationTripleMatcher
  extends Instrumented with Logger with LibConfig {
  
  private val triplestoreUrl= config.getString("mtner.triplestore.url");
  log.info("Triplestore URL: " + triplestoreUrl)

  val entityValueLookup = synchronized { buildLookupMaps(Seq.empty) }


  def findMatchingQuads(entities: Seq[String], relations: Seq[String], values: Seq[String]) = {

    val entityValueMatches = (for {
      entity <- entities
      entityLookup = entityValueLookup.get(entity)
      value <- values if entityLookup.isDefined
      valueLookup = entityLookup.get.get(value)
      if valueLookup.isDefined
      entityValueMatch = valueLookup.get
    } yield entityValueMatch).flatten


    entityValueMatches

  }

  def findEntityValueMatch(entity: String, value: String): Seq[KnownQuad] = {

    val valueLookup = entityValueLookup.getOrElse(entity,Map.empty[String,Seq[KnownQuad]])
    val entityValueMatch = valueLookup.getOrElse(value,Seq.empty[KnownQuad])

    entityValueMatch.seq
  }


  private def buildLookupMaps(predicates: Seq[String]) = {
    val lq = metrics.timer("loadQuadsFromTriplestore")
    val evlm = metrics.timer("entityValueLookupMap")
    val rvlm = metrics.timer("relationValueLookupMap")
    val knownQuads = lq.time { loadQuadsFromTripleStore("").toSeq.par }
    log.info(s"Loaded ${knownQuads.size} Quads in ${(lq.mean nanoseconds).toSeconds}s from Knowledge Base")

  val entityValueLookup = evlm.time {
    knownQuads.groupBy(_.subject)
     .map { case (subject,quads) => (subject,quads.groupBy(_.value)) }
  }
  log.info(s"Build entity value lookup in ${(evlm.mean nanoseconds).toSeconds}s")

//  val relationValueLookup = rvlm.time {
//    knownQuads.groupBy(_.predicate)
//      .map { case (subject, quads) => (subject, quads.groupBy(_.value))}
//  }
//  log.info(s"Build entity value lookup in ${(rvlm.mean nanoseconds).toSeconds}s")

    entityValueLookup
  }

  private def loadQuadsFromTripleStore(propertyUri: String) = {
    val knownQuadsQuery = LargeQuery("sampleQuadQuery", triplestoreUrl + "/company/sparql", (limit: Int, offset: Int) =>
      s"""
       |PREFIX dbpedia: <http://dbpedia.org/ontology/>
       |
       |SELECT ?s ?p ?o ?d
       |WHERE {
       |  ?sp <http://weisenburger.ch/singletonPropertyOf> ?p .
       |  ?s ?sp ?o .
       |  OPTIONAL { ?sp <http://weisenburger.ch/fromDate> ?d } .
       |  FILTER( ?p IN (
       |    <http://dbpedia.org/ontology/equity>,
       |    <http://dbpedia.org/ontology/revenue>,
       |    <http://dbpedia.org/ontology/assets>,
       |    <http://dbpedia.org/ontology/operatingIncome>,
       |    <http://dbpedia.org/ontology/numberOfEmployees>,
       |    <http://dbpedia.org/ontology/netIncome> ))
       |}
       |order by ?s ?p
       |OFFSET ${offset}
       |LIMIT ${limit}
      """.stripMargin)

    val queryResult: Seq[QuerySolution] = SparqlQueryExecutor.executeResAsSolution(knownQuadsQuery)
    // timex is optional
    val knownQuads = for {
      querySolution <-  queryResult
      subject = solutionNodeToString(querySolution.get("s"))
      predicate = solutionNodeToString(querySolution.get("p"))
      value = convertValueFormat(solutionNodeToString(querySolution.get("o")).get)
      timex = solutionNodeToString(querySolution.get("d"))
    } yield KnownQuad(subject.get, predicate.get, value, timex)

    knownQuads
  }

  // due to (in my opinion) the way the dbpedia extractor formats number strings
  // we have to unify the string representation
  private def convertValueFormat(tripleStoreFormatValueString: String) = {
    NumberFormatConverter.convertToFormattedNumberString(tripleStoreFormatValueString)
  }

  private def solutionNodeToString(n: RDFNode) = n match {
    case null => None
    case n if n.isLiteral => Some(n.asLiteral.getString)
    case n if n.isResource || n.isURIResource => Some(n.asResource.getURI)
  }


  case class KnownQuad(subject: String, predicate: String, value: String, timex: Option[String])


}
