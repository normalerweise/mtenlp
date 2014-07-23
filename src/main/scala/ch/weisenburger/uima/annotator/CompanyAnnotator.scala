package ch.weisenburger.uima.annotator

import ch.weisenburger.nlp.ner.CompanyRecognizer
import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
class CompanyAnnotator extends JCasAnnotator_ImplBase with UimaTypesSugar {

  override def initialize(aContext: UimaContext) = {
    // force init
    CompanyRecognizer.dictionaryChunker
  }

  override def process(jCas: JCas): Unit = {
    val text = jCas.getDocumentText


    val companyChunks = CompanyRecognizer.dictionaryChunker.chunk(text).chunkSet
    // resource uri of the entity the current text is about
    val dbpediaResourceURI = jCas.revision.getDbpediaResourceURI

    for(cChunk <- companyChunks) {
      val company = new ch.weisenburger.uima.types.distantsupervision.NamedEntity(jCas)
      company.setEntityType("COMPANY")
      company.setBegin(cChunk.start)
      company.setEnd(cChunk.end)

      cChunk.`type`() match {
        case CompanyRecognizer.GENERIC_MATCH => company.setDbpediaURI(dbpediaResourceURI)
        case dbpediaURI => company.setDbpediaURI(dbpediaURI)
      }


      company.addToIndexes(jCas)
    }


  }


}
