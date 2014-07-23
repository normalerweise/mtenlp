package ch.weisenburger.uima

import ch.weisenburger.uima.consumer.{SampleCandidateScalaCaseConverter, SampleScalaCaseConverter}
import ch.weisenburger.uima.types.distantsupervision
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas


/**
 * Created by Norman on 07.07.14.
 */
class TestPipeline private[uima] (
  private val _components: Seq[JCasAnnotator_ImplBase],
  private val jCas: JCas) {


  protected val components = _components


  def process(text: String, resourceURI: String): JCas = {
    jCas.reset
    jCas.setDocumentText(text)

    val revision = new distantsupervision.Revision(jCas)
    revision.setDbpediaResourceURI(resourceURI)
    revision.setWikipediaRevNumber(123456L)
    revision.setWikipediaArticleName("TEST")
    revision.addToIndexes()

    components.foreach(c => c.process(jCas))

    jCas

  }

}
