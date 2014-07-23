package ch.weisenburger.sparql

import ch.weisenburger.crosscutting_concerns.LibConfig
import com.hp.hpl.jena.query.{QueryExecutionFactory, QueryFactory, QueryParseException, QuerySolution}

import scala.collection.JavaConversions._


/**
 * Created by Norman on 11.05.14.
 */
object SparqlQueryExecutor extends LibConfig {

  val additionalPropertyBatchSize = config.getInt("mtner.sparql.additional_property_batch_size")
  val largeQueryBatchSize = config.getInt("mtner.sparql.large_query_batch_size")

  def execute(q: AnyQuery) = executeSPAQRL(q.sparqlEndpointUrl, q.queryString).toSeq

  def execute(resources: List[String], q: AdditionalPropertiesQuery): Iterator[QuerySolution] = {
    def queryUntilNoMoreResourcesLeft(resources: List[String], batchSize: Int, counter: Int = 1): Iterator[QuerySolution] = {
      val (currentBatch, remainingResources) = resources.splitAt(batchSize)
      if(currentBatch.size > 0) {
        println("Execute partial query no: " + counter)
        val resultSet = executeSPAQRL(q.sparqlEndpointUrl, q.queryStringInFilter(currentBatch))
        println("Finished partial query no: " + counter)
        resultSet ++ queryUntilNoMoreResourcesLeft(remainingResources, batchSize, counter + 1)
      } else {
        Iterator.empty
      }
    }
    queryUntilNoMoreResourcesLeft(resources, additionalPropertyBatchSize)
  }


   def execute(q: LargeQuery): List[String] = {
    def queryUntilNoResult(limit: Int, offset: Int): List[String] = {
      val resultSet = executeSPAQRL(q.sparqlEndpointUrl, q.queryStringWithLimitAndOffset(limit, offset))

      val resultResourceUris = collection.mutable.ListBuffer.empty[String]
      resultSet.hasNext match {
        case true => {
          while (resultSet.hasNext) {
            val element = resultSet.next()
            val resourceURI = element.get("x").toString
            resultResourceUris += resourceURI
          }
          // recursively continue
          resultResourceUris.toList ++ queryUntilNoResult(limit, offset + limit)
        }
        case false => List.empty[String]
      }
    }

    // start the execution
    queryUntilNoResult(limit = largeQueryBatchSize, offset = 0)
  }

  def executeResAsSolution(q: LargeQuery): Seq[QuerySolution] = {

    def queryUntilNoResult(limit: Int, offset: Int): Seq[QuerySolution] = {
      val resultSet = executeSPAQRL(q.sparqlEndpointUrl, q.queryStringWithLimitAndOffset(limit, offset))
      val results = resultSet.toSeq
      results.length match {
        case length if length > 0 =>
          // recursively continue
          results ++ queryUntilNoResult(limit, offset + limit)
        case _ => List.empty
      }
    }

    // start the execution
    queryUntilNoResult(limit = largeQueryBatchSize, offset = 0)
  }

  def executeResAsSolutionIter(q: LargeQuery): Iterator[QuerySolution] = {

    def queryUntilNoResult(limit: Int, offset: Int): Iterator[QuerySolution] = {
      val resultSet = executeSPAQRL(q.sparqlEndpointUrl, q.queryStringWithLimitAndOffset(limit, offset))
      val results = resultSet.toIterator
      results.length match {
        case length if length > 0 =>
          // recursively continue
          results ++ queryUntilNoResult(limit, offset + limit)
        case _ => Iterator.empty
      }
    }

    // start the execution
    queryUntilNoResult(limit = largeQueryBatchSize, offset = 0)
  }



  def executeOrCached(q: LargeQuery) =
    cachedResult(q.id) match {
      case Some(result) => result
      case None =>
        val result: List[String] = execute(q)
        cacheResult(result, q.id)
        result
  }

  private def executeSPAQRL(endpointUrl: String, queryString: String) = try {
    val query = QueryFactory.create(queryString);
    val dbPediaSparqlServiceForQuery = QueryExecutionFactory.sparqlService(endpointUrl, query)
    dbPediaSparqlServiceForQuery.execSelect
  } catch {
    case ex: QueryParseException => println(queryString); throw ex
  }

 private def cacheResult(result: List[String], queryId: String) = {
    import ch.weisenburger.deprecated_ner.FileUtil._
    writeOneElementPerLine(cacheFilePathForQueryId(queryId), result)
  }

  private def cachedResult(queryId: String) = {
    import ch.weisenburger.deprecated_ner.FileUtil._
    readMaybeFileOneElementPerLine(cacheFilePathForQueryId(queryId))
  }


}
