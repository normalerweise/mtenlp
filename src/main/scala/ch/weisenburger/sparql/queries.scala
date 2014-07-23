package ch.weisenburger.sparql

/**
 * Created by Norman on 14.04.14.
 */
abstract class Query {
 val id: String
 val sparqlEndpointUrl: String
}

case class LargeQuery(id: String, sparqlEndpointUrl: String, queryStringWithLimitAndOffset: (Int, Int) => String) extends Query
case class AnyQuery(id: String, sparqlEndpointUrl: String, queryString: String) extends Query

case class AdditionalPropertiesQuery(id: String, sparqlEndpointUrl: String, queryStringInFilter: (List[String]) => String) extends Query


