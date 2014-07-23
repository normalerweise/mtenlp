package ch.weisenburger.deprecated_ner

import java.io.{BufferedWriter, File, FileWriter}

import ch.weisenburger.sparql.{LargeQuery, SparqlQueryExecutor}

/**
 * Created by Norman on 14.04.14.
 */
object CompanyUriListGenerator extends App {

  val dbPediaSparqlEndpointUrlString = "http://dbpedia.org/ch.weisenburger.sparql"

  val resourcesOfTypeDBpediaCompanyQuery = LargeQuery("dbpedia-dbpedia-companies", dbPediaSparqlEndpointUrlString, (limit: Int, offset: Int) =>
    s"""
        PREFIX res: <http://dbpedia.org/ontology/>

        SELECT distinct ?x
        WHERE
          {
            {
              SELECT DISTINCT ?x
              WHERE
                {
                  ?x a res:Company
                } ORDER BY ASC(?x)
            }
          }
        OFFSET ${offset}
        LIMIT ${limit}
      """)

  val resourcesOfTypeYagoCompanyQuery = LargeQuery("dbpedia-yago-companies", dbPediaSparqlEndpointUrlString, (limit: Int, offset: Int) =>
    s"""
      PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>

        SELECT distinct ?x
        WHERE
          {
            {
              SELECT DISTINCT ?x
              WHERE
                {
                  ?x rdf:type <http://dbpedia.org/class/yago/Company108058098> .
                } ORDER BY ASC(?x)
            }
          }
        OFFSET ${offset}
        LIMIT ${limit}
      """)

  println("start")
  val dbPediaResourcesOfTypeDBpediaCompany = SparqlQueryExecutor.executeOrCached(resourcesOfTypeDBpediaCompanyQuery)
  val dbPediaResourcesOfTypeDBpediaCompanySize = dbPediaResourcesOfTypeDBpediaCompany.size

  val dbPediaResourcesOfTypeYagoCompany = SparqlQueryExecutor.executeOrCached(resourcesOfTypeYagoCompanyQuery)
  val dbPediaResourcesOfTypeYagoCompanySize = dbPediaResourcesOfTypeYagoCompany.size

  val airpediaRescourcesOfTypeDBpediaCompany = GZippedNtReader.readOrCacheAirpediaDbpediaCompaniesWithConfidenceGe9("resources/airpedia-classes-en.nt.gz").map(_.replace("%","%25").replace("\"", "%22"))
  val airpediaRescourcesOfTypeDBpediaCompanySize = airpediaRescourcesOfTypeDBpediaCompany.size


  val dbpediaCompanyPlusAirpediaCompany = (dbPediaResourcesOfTypeDBpediaCompany ++ airpediaRescourcesOfTypeDBpediaCompany).toSet
  val dbpediaCompanyPlusAirpediaCompanySize = dbpediaCompanyPlusAirpediaCompany.size

  val dbpediaCompanyPlusYagoCompany = (dbPediaResourcesOfTypeDBpediaCompany ++ dbPediaResourcesOfTypeYagoCompany).toSet
  val dbpediaCompanyPlusYagoCompanySize = dbpediaCompanyPlusYagoCompany.size

  val airpediaCompanyPlusYagoCompany = (airpediaRescourcesOfTypeDBpediaCompany ++ dbPediaResourcesOfTypeYagoCompany).toSet
  val airpediaCompanyPlusYagoCompanySize = airpediaCompanyPlusYagoCompany.size

  val merged = (dbPediaResourcesOfTypeDBpediaCompany ++ dbPediaResourcesOfTypeYagoCompany ++ airpediaRescourcesOfTypeDBpediaCompany).toSet.toList.sorted

  val writer = new BufferedWriter(new FileWriter(new File("data/companyUris.txt")))
  for( resourceUri <- merged) yield {
    writer.write(resourceUri)
    writer.write("\n")
  }

  println(s"DBpedia DBpedia Company size: $dbPediaResourcesOfTypeDBpediaCompanySize")
  println(s"DBpedia Yago Company size: $dbPediaResourcesOfTypeYagoCompanySize")
  println(s"Airpedia DBpedia Company size: $airpediaRescourcesOfTypeDBpediaCompanySize")
  println(s"Merged size: ${merged.size}")
  println(s"Additive size: ${dbPediaResourcesOfTypeDBpediaCompanySize + dbPediaResourcesOfTypeYagoCompanySize + airpediaRescourcesOfTypeDBpediaCompanySize}")

  println(s"Overlap DBpedia DBpedia Company + DBpedia Yago Company: " +
    overlap(dbpediaCompanyPlusYagoCompanySize, dbPediaResourcesOfTypeDBpediaCompanySize, dbPediaResourcesOfTypeYagoCompanySize))

  println(s"Overlap DBpedia DBpedia Company + Airpedia DBpedia Company : " +
    overlap(dbpediaCompanyPlusAirpediaCompanySize, dbPediaResourcesOfTypeDBpediaCompanySize, airpediaRescourcesOfTypeDBpediaCompanySize))

  println(s"Overlap DBpedia Yago Company + Airpedia DBpedia Company : " +
    overlap(airpediaCompanyPlusYagoCompanySize, dbPediaResourcesOfTypeYagoCompanySize, airpediaRescourcesOfTypeDBpediaCompanySize))


  private def overlap(mergedSize: Int, singleSizeA: Int, singleSizeB: Int) = {
    1 - (mergedSize.toDouble / (singleSizeA + singleSizeB))
  }

}
