package ch.weisenburger.deprecated_ner

import java.text.{DecimalFormat, NumberFormat}
import java.util.{Locale, UUID}

import ch.weisenburger.crosscutting_concerns.Instrumented
import ch.weisenburger.sparql.{LargeQuery, SparqlQueryExecutor}
import com.aliasi.chunk.Chunk
import com.aliasi.dict.{DictionaryEntry, ExactDictionaryChunker, MapDictionary, TrieDictionary}
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory
import com.hp.hpl.jena.query.QuerySolution
import com.hp.hpl.jena.rdf.model.RDFNode
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

/**
 * Created by Norman on 11.05.14.
 */
object LingPipeTrainingGenerator extends Instrumented {

  val logger = LoggerFactory.getLogger(this.getClass);


  val conf = ConfigFactory.load();
  val triplestoreUrl= conf.getString("mtner.triplestore.url");
  logger.info("Triplestore URL: " + triplestoreUrl)

  var entityShadowDictionary: Map[String, Util.CompanyWithSurfaceStings]  = _
  var entityChunkerDictionary: MapDictionary[String]= _
  var valueShadowDictionary: Map[String, QuadOccurrence] = _
  var valueChunkerDictionary: TrieDictionary[String] = _
  var entityChunker: ExactDictionaryChunker = _
  var valueChunker: ExactDictionaryChunker = _

  def init(predicates: Seq[String]) = {
  val edic = loadEntityDictionaries
  entityShadowDictionary = edic._1
  entityChunkerDictionary = edic._2

  logger.info("Entity dictionary size: " + entityChunkerDictionary.size)

  val sdic = loadSampleTripleDictionary(predicates)
  valueShadowDictionary = sdic._1
  valueChunkerDictionary = sdic._2

  logger.info("Value dictionary size: " + valueChunkerDictionary.size)

  entityChunker = new ExactDictionaryChunker(entityChunkerDictionary,
    IndoEuropeanTokenizerFactory.INSTANCE, true, true)


  //  val editDistance = new FixedWeightEditDistance(0,-1,-1,-1,-1);
  //  val maxDistance = 1.0;
  //  val valueChunker = new ApproxDictionaryChunker(valueChunkerDictionary,IndoEuropeanTokenizerFactory.INSTANCE,
  //    editDistance,maxDistance);

  valueChunker = new ExactDictionaryChunker(valueChunkerDictionary,
    IndoEuropeanTokenizerFactory.INSTANCE, true, true)
  }
//  val text = "Apple worldwide annual revenue in 2010 totaled $65 billion, growing to $156 billion in 2012."
//  val result = findSamplesInRevision(text)
//  println(result.mkString("\n\n"))
//
//
//  Registry.reporter.report()

  /////////////////////////////////////////////////////////////////////////////////
  val keyEntityPrefix = "ENTITY:"
  val quadPrefix = "TRIPLE:"

  def findSamplesInRevision(revisionText: String, revision: String, pageId: String) = for {
    sentence <- SentenceSplitter.split(revisionText)
    result <- findSamplesInSentence(sentence)
    relation = result._1._1.predicate
    timex = result._1._1.timex
    entity = result._2._1.uri
    entityStartPos = result._2._2.start
    entityEndPos = result._2._2.end
    value = result._1._1.value
    valueStartPos = result._1._2.start
    valueEndPos = result._1._2.end
  } yield {
    Sample(relation, entity, entityStartPos, entityEndPos, value, valueStartPos, valueEndPos, timex.getOrElse(""), sentence, revision, pageId)
  }

  def findSamplesInSentence(sentence: String) = {
    val et = metrics.timer("entityChunker.chunk")
    val vt = metrics.timer("valueChunker.chunk")
    val mt = metrics.timer("findMatches")
    val entities = et.time {
    entityChunker.chunk(sentence).chunkSet.map(e => (entityShadowDictionary(e.`type`), e)).toSet
    }
    val values = vt.time {
      valueChunker.chunk(sentence).chunkSet.map(e => (valueShadowDictionary(e.`type`), e)).toSet
    }

    val matches = mt.time {
      findMatches(entities, values)
    }
    val results = matches.map(m => (m._1, m._2))
    results
  }

  def findMatches(entities: Set[(Util.CompanyWithSurfaceStings, Chunk)], values: Set[(QuadOccurrence, Chunk)]) = {
    for {
      value <- values
      quad = value._1
      entity <- entities if entity._1.uri == quad.subject
    } yield {
      (value, entity)
    }
  }

  def loadEntityDictionaries = {

    val CHUNK_SCORE = 1.0;
    val dictionary = new MapDictionary[String]

    val lt = metrics.timer("loadUriSurfaceStringMap")
    val bt = metrics.timer("buildDictionary")

    val entitiesWithSurfaceStrings = lt.time {
      Util.loadUriSurfaceStringMapFromJar()
    }

    bt.time {
      for {
        entityTuple <- entitiesWithSurfaceStrings
        entity = entityTuple._2
        surfaceString <- entity.surfaceStrings if surfaceString.length >= 3 // surface strings <3 reduce ner precision
      } yield
        dictionary.addEntry(
          new DictionaryEntry[String](surfaceString, entity.title, CHUNK_SCORE))
    }

    (entitiesWithSurfaceStrings, dictionary)
  }

  def loadSampleTripleDictionary(predicates: Seq[String]) = {
    val CHUNK_SCORE = 1.0;
    val dictionary = new TrieDictionary[String]

    val quadMap = (for {
      predicate <- predicates
      quadTuple <- loadQuadsFromTripleStore(predicate)
      quad = quadTuple._2
      quadValueSurfaceStrings <- generateSurfaceStrings(quad)
    } yield {
      dictionary.addEntry(
        new DictionaryEntry[String](quadValueSurfaceStrings, quad.uuid, CHUNK_SCORE))

      quadTuple
    }).toMap
    (quadMap, dictionary)
  }

  def loadQuadsFromTripleStore(propertyUri: String) = {
    val sampleQuadQuery = LargeQuery("sampleQuadQuery", triplestoreUrl + "/company/ch.weisenburger.sparql", (limit: Int, offset: Int) =>
      s"""
       |PREFIX dbpedia: <http://dbpedia.org/ontology/>
       |
       |SELECT ?s ?p ?o ?d
       |WHERE {
       |  ?sp <http://weisenburger.ch/singletonPropertyOf> ?p .
       |  ?sp <http://weisenburger.ch/fromDate> ?d.
       |  ?s ?sp ?o .
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
    val queryResult: Seq[QuerySolution] = SparqlQueryExecutor.executeResAsSolution(sampleQuadQuery)
    queryResult.map { querySolution =>
      val uuid = UUID.randomUUID.toString
      val subject = solutionNodeToString(querySolution.get("s"))
      val predicate = propertyUri
      val value = solutionNodeToString(querySolution.get("o"))
      val timex = solutionNodeToString(querySolution.get("d"))
      (uuid -> QuadOccurrence(uuid, subject, predicate, value, Some(timex)))
    }.toMap
  }

  def solutionNodeToString(n: RDFNode) = n match {
    case n if n.isLiteral => n.asLiteral.getString
    case n if n.isResource || n.isURIResource => n.asResource.getURI
  }

  def generateSurfaceStrings(quad: QuadOccurrence) = quad.predicate match {
    case "http://dbpedia.org/ontology/revenue" => generateNumericDoubleStrings(quad.value)
    case "http://dbpedia.org/ontology/numberOfEmployees" => generateNumericIntStrings(quad.value)
    case _ => Seq(quad.value)
  }

  def generateNumericIntStrings(value: String): Seq[String] = {
    Seq(
      value,
      NumberFormat.getNumberInstance(Locale.US).format(value.toLong),
      NumberFormat.getNumberInstance(Locale.GERMANY).format(value.toLong))
  }

  def generateNumericDoubleStrings(value: String): Seq[String] = {
    val decimalFormat = new DecimalFormat("#");
    val valueAsDouble = value.toDouble
    val formattedDouble = decimalFormat.format(valueAsDouble)
    val doubleWithoutTrailingZeros = formattedDouble.replaceAll("0*$", "")

    val largestUnitDigits = {
      val mod = formattedDouble.length % 3; if (mod == 0) 3 else mod
    }
    val unitDigits = formattedDouble.length - largestUnitDigits

    val surfaceStrings = largestUnitDigits match {
      case 0 if doubleWithoutTrailingZeros.length <= 3 => roundedDecimalString(formattedDouble, unitDigits)
      case 0 if doubleWithoutTrailingZeros.length > 3 => exactAndRoundedDecimalStrings(formattedDouble, unitDigits, doubleWithoutTrailingZeros.length)
      case d if doubleWithoutTrailingZeros.length <= d => roundedDecimalString(formattedDouble, unitDigits)
      case d if doubleWithoutTrailingZeros.length > d => exactAndRoundedDecimalStrings(formattedDouble, unitDigits, doubleWithoutTrailingZeros.length)
      case _ => throw new UnsupportedOperationException(s"should not happen: $formattedDouble")
    }
    surfaceStrings
  }

  def exactAndRoundedDecimalStrings(formattedDouble: String, preDecimalDigits: Int, totalDigits: Int) = {
    val roundedLargestUnitString = roundedDecimalString(formattedDouble, preDecimalDigits).head
    val exactLargestUnitString = roundedLargestUnitString + "." + formattedDouble.substring(formattedDouble.length - preDecimalDigits, totalDigits);

    Seq(roundedLargestUnitString, exactLargestUnitString)
  }

  def roundedDecimalString(formattedDouble: String, unitDigits: Int) = Seq(formattedDouble.substring(0, formattedDouble.length - unitDigits))

  trait EntityType

  case class NamedEntity(title: String, uri: String) extends EntityType

  case class QuadOccurrence(uuid: String, subject: String, predicate: String, value: String, timex: Option[String]) extends EntityType

  case class SampleSentece(subject: String, predicate: String, value: String, sentenceString: String, subjectStart: Int, subjectEnd: Int)

}
