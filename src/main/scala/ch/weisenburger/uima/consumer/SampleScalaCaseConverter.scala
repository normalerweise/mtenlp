package ch.weisenburger.uima.consumer

import ch.weisenburger.uima.types.distantsupervision.skala.{Quad, Sample}
import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
class SampleScalaCaseConverter extends JCasAnnotator_ImplBase with UimaTypesSugar {

  import ch.weisenburger.uima.types.distantsupervision.skala.SkalaTypeConversions._

  var samples: scala.collection.immutable.Seq[Sample] = _

  override def process(jCas: JCas): Unit = {

    val revision = jCas.revision
    val revisionNumber = revision.getWikipediaRevNumber
    val articleName = revision.getWikipediaArticleName

    samples = (for {
      sample <- jCas.samples
      s = sample.getSentcene
      sentenceText = s.getCoveredText
      sEntity = eToScalaType.apply(sample.getSentenceSubject,s)
      sRelation = Option(rToScalaType.apply(sample.getSentencePredicate,s))
      sValue = vToScalaType.apply(sample.getSentenceObject, s)
      sTimex = Option(tToScalaType.apply(sample.getSentenceTimex, s))
      qEntity = sample.getQuadSubject
      qRelation = sample.getQuadPredicate
      qValue = sample.getQuadObject
      qTimex = Option(sample.getQuadTimex)
    } yield Sample(sentenceText, articleName, Seq(revisionNumber), Quad(qEntity, qRelation, qValue, qTimex),
             sEntity, sRelation, sValue, sTimex)
      ).toList
  }

}
