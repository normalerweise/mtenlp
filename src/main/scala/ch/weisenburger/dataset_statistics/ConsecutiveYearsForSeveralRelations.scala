package ch.weisenburger.dataset_statistics

import ch.weisenburger.sparql.{AnyQuery, SparqlQueryExecutor}
import com.hp.hpl.jena.query.QuerySolution
import com.typesafe.config.ConfigFactory

/**
 * Created by Norman on 11.05.14.
 */
object ConsecutiveYearsForSeveralRelations extends App {

  val conf = ConfigFactory.load();
  val triplestoreUrl= conf.getString("mtner.triplestore.url");

  val relations = Seq(
    "http://dbpedia.org/ontology/equity",
    "http://dbpedia.org/ontology/revenue",
    "http://dbpedia.org/ontology/assets",
    "http://dbpedia.org/ontology/operatingIncome",
    "http://dbpedia.org/ontology/numberOfEmployees",
    "http://dbpedia.org/ontology/netIncome")

  val years = Seq(
    "2013",
    "2012",
    "2011")

  val relationByYearQuery = (relation: String, year: String) => AnyQuery("relation-by-year-query", triplestoreUrl + "/company/sparql",
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

  val results = for{
    relation <- relations
    year <- years
  } yield resolveQueryVariable(SparqlQueryExecutor.execute(relationByYearQuery(relation, year)))

  val intersection = results.reduceLeft( (e1,e2) => e1 & e2)

  println(intersection.toList.sorted.mkString("\n"))
  println("Companies with three consecutive years for " + intersection.size)

  private def resolveQueryVariable(results: Seq[QuerySolution]) =
    (for {
      e <- results
      resourceURI = e.get("s").asResource.getURI
    } yield resourceURI ).toSet

}
