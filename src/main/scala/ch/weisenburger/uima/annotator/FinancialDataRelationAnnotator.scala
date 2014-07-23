package ch.weisenburger.uima.annotator

import ch.weisenburger.nlp.ner.FinancialDataRelationRecognizer
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._


/**
 * Created by Norman on 08.07.14.
 */
class FinancialDataRelationAnnotator extends JCasAnnotator_ImplBase {

  override def initialize(aContext: UimaContext) = {
    // force init
    FinancialDataRelationRecognizer.dictionaryChunker
  }

  override def process(jCas: JCas): Unit = {

    val chunker = FinancialDataRelationRecognizer.dictionaryChunker

    val relationChunks = chunker.chunk(jCas.getDocumentText).chunkSet

    for(relationChunk <- relationChunks) {
      val relation = new ch.weisenburger.uima.types.distantsupervision.Relation(jCas)

      relation.setBegin(relationChunk.start)
      relation.setEnd(relationChunk.end)
      relation.setOntologyURI(relationChunk.`type`)
      relation.addToIndexes(jCas)
    }

  }

}
