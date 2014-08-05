package ch.weisenburger.dataset_statistics

import ch.weisenburger.sparql.{AnyQuery, SparqlQueryExecutor}
import com.hp.hpl.jena.query.QuerySolution
import com.typesafe.config.ConfigFactory

/**
 * Created by Norman on 11.05.14.
 */
object ConsecutiveYears extends App {

  val conf = ConfigFactory.load();
  val triplestoreUrl= conf.getString("mtner.triplestore.url");

//  val relation = "http://dbpedia.org/ontology/revenue"
//  val relation = "http://dbpedia.org/ontology/equity"
//  val relation = "http://dbpedia.org/ontology/assets"
//  val relation = "http://dbpedia.org/ontology/operatingIncome"
//  val relation = "http://dbpedia.org/ontology/numberOfEmployees"
  val relation = "http://dbpedia.org/ontology/netIncome"

  val relationByYearQuery = (year: String) => AnyQuery("relation-by-year-query", triplestoreUrl + "/company/sparql",
    s"""
      |SELECT DISTINCT ?s
      |WHERE {
      |  BIND( <$relation> AS ?p)
      |  ?sp <http://weisenburger.ch/singletonPropertyOf> ?p.
      |  ?sp <http://weisenburger.ch/fromDate> "$year"^^<xsd:String>.
      |  ?s ?sp ?o .
      |}
      |order by ?s
    """.stripMargin)


  val companiesWithValueFor2011 = resolveQueryVariable(SparqlQueryExecutor.execute(relationByYearQuery("2011")))
  val companiesWithValueFor2012 = resolveQueryVariable(SparqlQueryExecutor.execute(relationByYearQuery("2012")))
  val companiesWithValueFor2013 = resolveQueryVariable(SparqlQueryExecutor.execute(relationByYearQuery("2013")))

  val intersection = (companiesWithValueFor2011 & companiesWithValueFor2012 & companiesWithValueFor2013).toList.sorted

  println(intersection.mkString("\n"))
  println("Companies with three consecutive years for " + relation + ": " + intersection.size)

  private def resolveQueryVariable(results: Seq[QuerySolution]) =
    (for {
      e <- results
      resourceURI = e.get("s").asResource.getURI
    } yield resourceURI ).toSet

}
