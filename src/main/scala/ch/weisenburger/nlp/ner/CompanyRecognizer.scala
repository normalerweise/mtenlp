package ch.weisenburger.nlp.ner

import ch.weisenburger.crosscutting_concerns.{Instrumented, Logger}
import ch.weisenburger.deprecated_ner.Util
import com.aliasi.dict.{DictionaryEntry, ExactDictionaryChunker, MapDictionary}
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory

import scala.concurrent.duration._

/**
 * Created by Norman on 08.07.14.
 */
object CompanyRecognizer extends Logger with Instrumented {

  // Constant which indicates a match,
  // that needs to be disambiguated
  val GENERIC_MATCH = "generic"

  lazy val dictionaryChunker = synchronized { buildDictionaryChunker }


  private def buildDictionaryChunker = {
    val bt = metrics.timer("buildCompanyDictionaryChunker")
    val chunker = bt.time { new ExactDictionaryChunker(buildDictionary,
      IndoEuropeanTokenizerFactory.INSTANCE, true, true) }
    log.info(s"Initialized Dictionary Company Recognizer (duration: ${(bt.mean nanoseconds).toSeconds}s)")
    chunker
  }

  private def buildDictionary = {
    def e(surfaceString: String, entityId: String) = {
      val CHUNK_SCORE = 1.0
      new DictionaryEntry[String](surfaceString, entityId, CHUNK_SCORE)
    }

    val dictionary = new MapDictionary[String]

    { // add entries from surface String XML
      val entitiesWithSurfaceStrings = Util.loadUriSurfaceStringMap

      // Exclude surface strings with less than three chars -> too much dirt
      for {
        entityTuple <- entitiesWithSurfaceStrings
        entity = entityTuple._2
        surfaceString <- entity.surfaceStrings if surfaceString.length >= 3
      } yield dictionary addEntry e(surfaceString, entity.uri)
    }

    { // Add generic entries
      // We match case sensitive
      // -> e.g. avoid machtes like 'total' as the company Total S. A.
      // so we have to add the same generic surface string in all case combinations
      dictionary addEntry e("it", GENERIC_MATCH)
      dictionary addEntry e("its", GENERIC_MATCH)
      dictionary addEntry e("It", GENERIC_MATCH)
      dictionary addEntry e("Its", GENERIC_MATCH)
      dictionary addEntry e("company", GENERIC_MATCH)
      dictionary addEntry e("Company", GENERIC_MATCH)
      dictionary addEntry e("group", GENERIC_MATCH)
      dictionary addEntry e("Group", GENERIC_MATCH)
      dictionary addEntry e("airline", GENERIC_MATCH)
      dictionary addEntry e("Airline", GENERIC_MATCH)
      dictionary addEntry e("credit union", GENERIC_MATCH)
      dictionary addEntry e("Credit Union", GENERIC_MATCH)
      dictionary addEntry e("firm", GENERIC_MATCH)
      dictionary addEntry e("Firm", GENERIC_MATCH)
    }

    dictionary
  }


}
