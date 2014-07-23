package ch.weisenburger.uima

import ch.weisenburger.uima.types.distantsupervision
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

/**
 * Created by Norman on 07.07.14.
 */
class FinancialDataSampleConsolePipeline private[uima] (
  protected val components: Seq[JCasAnnotator_ImplBase],
  private val jCas: JCas) extends PipelineStatistics {


  def process(text: String,resourceURI: String, wikipediaRevNumber: Long) = try {

    jCas.setDocumentText(text)

    val revision = new distantsupervision.Revision(jCas)
    revision.setDbpediaResourceURI(resourceURI)
    revision.setWikipediaRevNumber(wikipediaRevNumber)
    revision.addToIndexes()

    components.foreach(c => c.process(jCas))

  } finally {
    jCas.reset
  }

}
