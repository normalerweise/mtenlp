package ch.weisenburger.dataset_statistics

import ch.weisenburger.sparql.{AnyQuery, LargeQuery, SparqlQueryExecutor}
import com.hp.hpl.jena.query.QuerySolution
import com.typesafe.config.ConfigFactory

/**
  * Created by Norman on 11.05.14.
  */
object CompaniesMissingComparedToDBpedia extends App {

   val conf = ConfigFactory.load();
   val mteTriplestoreUrl= conf.getString("mtner.triplestore.url") + "/company/ch.weisenburger.sparql";
   println(s"mteTriplestoreUrl: $mteTriplestoreUrl")

   val relations = Seq(
     "http://dbpedia.org/ontology/equity",
     "http://dbpedia.org/ontology/revenue",
     "http://dbpedia.org/ontology/assets",
     "http://dbpedia.org/ontology/operatingIncome",
     "http://dbpedia.org/ontology/numberOfEmployees",
     "http://dbpedia.org/ontology/netIncome").map( r => s"<$r>")


   val dbpediaCompaniesWithKeyFiguresQuery = LargeQuery("companies-with-key-figures-dbp",  "http://dbpedia.org/ch.weisenburger.sparql",
     (limit: Int, offset: Int) => s"""
       |PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
       |SELECT distinct ?s
       |WHERE {
       | ?s ?p ?o .
       | FILTER( ?p IN ( ${relations.mkString(", ")} ))
       | {
       |   SELECT ?s
       |   WHERE {
       |     ?s a dbpedia-owl:Company .
       |   } ORDER BY ASC(?s)
       | }
       |}
       |OFFSET ${offset}
       |LIMIT ${limit}
     """.stripMargin)

  val mterCompaniesWithKeyFiguresQuery = AnyQuery("companies-with-key-figures-mter",  mteTriplestoreUrl,
    s"""
       |PREFIX dbpedia-owl: <http://dbpedia.org/ontology/>
       |SELECT distinct ?s
       |WHERE {
       | ?s ?sp ?o .
       | ?sp <http://weisenburger.ch/singletonPropertyOf> ?p .
       | FILTER( ?p IN ( ${relations.mkString(", ")} ))
       | {
       |   SELECT ?s
       |   WHERE {
       |     ?s a dbpedia-owl:Company .
       |   }
       | }
       |}
     """.stripMargin)


  println("query local")
//  private val enRes = """^http://en.dbpedia.org/resource/(.*)$""".r
  val mterCompaniesWithKeyFigures =
    resolveQueryVariable(SparqlQueryExecutor.execute(mterCompaniesWithKeyFiguresQuery))
//      .map(s => s match {
//      case enRes(t) => {
//        s"http://dbpedia.org/resource/$t"
//      }
//      case _ => s
//    })

  println("query dbpedia")
  val dbpediaCompaniesWithKeyFigures =
    resolveQueryVariable(SparqlQueryExecutor.executeResAsSolution(dbpediaCompaniesWithKeyFiguresQuery))

  println("diff")
  val companiesNotInMterSet = dbpediaCompaniesWithKeyFigures &~ mterCompaniesWithKeyFigures
  val companiesNotInDBPediaSet = mterCompaniesWithKeyFigures &~ dbpediaCompaniesWithKeyFigures

  println("Results:")
  println("Companies with key figures in DBpedia: " + dbpediaCompaniesWithKeyFigures.size)
  println("Companies with key figures in Mter: " +  mterCompaniesWithKeyFigures.size)
  println(s"Dbpedia - Mter difference size ${companiesNotInMterSet.size}(cont: ${dbpediaCompaniesWithKeyFigures.size -  mterCompaniesWithKeyFigures.size}} )")
  println(s"Mter - Dbpedia difference size ${companiesNotInDBPediaSet.size}(cont: ${mterCompaniesWithKeyFigures.size - dbpediaCompaniesWithKeyFigures.size}} )")

  val intersection = dbpediaCompaniesWithKeyFigures intersect mterCompaniesWithKeyFigures
  println(s"Shared companies ${intersection.size}")

  println("\n\nCompanies in DBpedia but not in Mter")
  println(companiesNotInMterSet.mkString("\n"))


  println("\n\nCompanies in Mter but not in DBpedia")
  println(companiesNotInDBPediaSet.mkString("\n"))






  /** The extractor returns resource quads with the english resource uri
    *  In order to compare with dbpedia.org ch.weisenburger.sparql endpoint
    * @return
    */
  //private val transformResourceUri: (String)=>String = (s) =>


   private def resolveQueryVariable(results: Seq[QuerySolution]) =
     (for {
       e <- results
       resourceURI = e.get("s").asResource.getURI
     } yield resourceURI ).toSet

 }
