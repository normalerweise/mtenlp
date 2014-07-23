package ch.weisenburger.nlp.ner

import com.aliasi.dict.{DictionaryEntry, ExactDictionaryChunker, MapDictionary}
import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory

/**
 * Created by Norman on 08.07.14.
 */
object FinancialDataRelationRecognizer {

  lazy val dictionaryChunker = synchronized { buildDictionaryChunker }


  private def buildDictionaryChunker = {
    new ExactDictionaryChunker(buildDictionary,
      IndoEuropeanTokenizerFactory.INSTANCE, true,true);
  }

  private def buildDictionary: MapDictionary[String] = {
    def e(surfaceString: String)(implicit ontologyURI: String) = {
      val CHUNK_SCORE = 1.0
      new DictionaryEntry[String](surfaceString, ontologyURI, CHUNK_SCORE)
    }

    val dict = new MapDictionary[String]

    { // add equity entries
      implicit val ontologyURI = "http://dbpedia.org/ontology/equity"
      dict addEntry e("equity")
    }

    { //addRevenueEntries
      implicit val ontologyURI = "http://dbpedia.org/ontology/revenue"
      dict addEntry e("revenue")
      dict addEntry e("revenues")
    }

    { // addAssetsEntries
      implicit val ontologyURI = "http://dbpedia.org/ontology/assets"
      dict addEntry e("assets")
    }

   {  //  addOperatingIncomeEntries
     implicit val ontologyURI = "http://dbpedia.org/ontology/operatingIncome"
      dict addEntry e("operating income")
    }

    { // addNetIncomeEntries
      implicit val ontologyURI = "http://dbpedia.org/ontology/netIncome"
      dict addEntry e("net income")
      dict addEntry e("net profit")
      dict addEntry e("lucre")
      dict addEntry e("profit")
      dict addEntry e("profits")
      dict addEntry e("earnings")
    }

    {
      // addNmberOfEmployeesEntries
      implicit val ontologyURI = "http://dbpedia.org/ontology/numberOfEmployees"
      dict addEntry e("employees")
      dict addEntry e("employed")
      dict addEntry e("employs")
      dict addEntry e("staff")
      dict addEntry e("number of employees")
    }

    dict
  }
}
