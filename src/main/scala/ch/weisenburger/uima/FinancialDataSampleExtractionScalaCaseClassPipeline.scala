package ch.weisenburger.uima

import ch.weisenburger.uima.consumer.{SampleCandidateScalaCaseConverter, SampleScalaCaseConverter}
import ch.weisenburger.uima.types.distantsupervision
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas


/**
 * Created by Norman on 07.07.14.
 */
class FinancialDataSampleExtractionScalaCaseClassPipeline private[uima] (
                                                           private val annotatorComponents: Seq[JCasAnnotator_ImplBase],
                                                           private val jCas: JCas,
                                                           private val sampleScalaCaseConverter: SampleScalaCaseConverter,
                                                           private val sampleCandidateScalaCaseConverter: SampleCandidateScalaCaseConverter) {


  protected val components = annotatorComponents ++ Seq(sampleScalaCaseConverter, sampleCandidateScalaCaseConverter)


  def process(wikipediaTextOfRevision: String, dbpediaResourceURI: String,
              wikipediaRevNumber: Long, wikipediaArticleName: String):
                (Seq[distantsupervision.skala.Sample], Seq[distantsupervision.skala.SampleCandidate]) = try {

    jCas.setDocumentText(wikipediaTextOfRevision)

    val revision = new distantsupervision.Revision(jCas)
    revision.setDbpediaResourceURI(dbpediaResourceURI)
    revision.setWikipediaRevNumber(wikipediaRevNumber)
    revision.setWikipediaArticleName(wikipediaArticleName)
    revision.addToIndexes()

    components.foreach(c => c.process(jCas))

    (sampleScalaCaseConverter.samples, sampleCandidateScalaCaseConverter.sampleCandidates)
  } finally {
    jCas.reset
  }

}