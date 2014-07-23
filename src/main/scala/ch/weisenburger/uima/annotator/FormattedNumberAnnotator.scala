package ch.weisenburger.uima.annotator

import ch.weisenburger.nlp.ner.FormattedNumberRecognizer
import ch.weisenburger.nlp.surface_string_parser.FormattedNumberParser
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

/**
 * Created by Norman on 08.07.14.
 */
class FormattedNumberAnnotator extends JCasAnnotator_ImplBase {

  override def initialize(aContext: UimaContext) = {
    // NOOP
  }

  override def process(jCas: JCas): Unit = {
    val text = jCas.getDocumentText
    val formattedNumberChunks = FormattedNumberRecognizer.chunkAll(text)

    for(fChunk <- formattedNumberChunks) {
      val value = new ch.weisenburger.uima.types.distantsupervision.Value(jCas)

      value.setBegin(fChunk.start)
      value.setEnd(fChunk.end)
      value.setValueType(fChunk.`type`)
      val surfaceString = FormattedNumberRecognizer.getSurfaceString(fChunk)(text)
      value.setParsedNumericValue(FormattedNumberParser.parseValue(surfaceString).toString)

      value.addToIndexes(jCas)
    }


  }


}
