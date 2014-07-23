package ch.weisenburger.deprecated_ner

import java.net.URLDecoder

import ch.weisenburger.sparql.{AdditionalPropertiesQuery, SparqlQueryExecutor}

/**
 * Created by Norman on 11.05.14.
 */
object LabelReader extends App {

   import ch.weisenburger.deprecated_ner.Util._

  val labelQuery = AdditionalPropertiesQuery("surface-strings-query", "http://dbpedia.org/ch.weisenburger.sparql", (resourceUris: List[String]) =>
    s"""
      |PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
      |PREFIX res: <http://dbpedia.org/ontology/>
      |PREFIX owl: <http://www.w3.org/2002/07/owl#>
      |
      |SELECT ?x ?y
      |WHERE {
      |  FILTER( ?x IN ( ${resourceUris.map( uri => "<" + uri + ">").mkString(", ")} ))
      |  { ?x rdfs:label ?y } UNION { ?x ^res:wikiPageRedirects ?y }
      |}
      |
      |
    """.stripMargin)

    val companyResources = FileUtil.readFileOneElementPerLine("data/companyUris.txt")
    val companyResourcesWithSurfaceStringCandidates = SparqlQueryExecutor.execute(companyResources, labelQuery)

    val companyResourceSurfaceStringTuples = (for {
      e <- companyResourcesWithSurfaceStringCandidates
      resourceURI = e.get("x")
      surfaceStringCandidate = e.get("y")
    } yield {
       val resourceURIString = resourceURI.asResource.getURI
       val surfaceString = surfaceStringCandidate match {
        case c if c.isLiteral => c.asLiteral.getString
        case c if c.isResource => extractSurfaceStringFromURI(c.asResource().getURI)
       }
      (resourceURIString, surfaceString)
    }).toSeq

    val companyNERModelAsXML = companyResourceSurfaceStringTuples.groupBy(_._1).toSeq.sortBy(_._1).map { resWithLabels =>
      val resUri = resWithLabels._1
      val labels = resWithLabels._2.map(_._2).toSet
      <company uri={resUri} titel={getLastUriComponent(resUri)}>
        {for (label <- labels) yield <surfaceString>{label}</surfaceString>}
      </company>
    }
  val rootNode = <companies> </companies>.copy(child = companyNERModelAsXML)
  saveUriSurfaceStringXML(rootNode)




  private def extractSurfaceStringFromURI(uri: String) = {
    URLDecoder.decode(getLastUriComponent(uri), "UTF-8")
      .replace("_", " ")
  }

}
