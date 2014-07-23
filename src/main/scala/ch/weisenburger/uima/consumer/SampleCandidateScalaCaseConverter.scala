package ch.weisenburger.uima.consumer

import ch.weisenburger.uima.types.distantsupervision.skala.SampleCandidate
import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._



/**
 * Created by Norman on 08.07.14.
 */
class SampleCandidateScalaCaseConverter extends JCasAnnotator_ImplBase with UimaTypesSugar {

 import ch.weisenburger.uima.types.distantsupervision.skala.SkalaTypeConversions._

  var sampleCandidates: scala.collection.immutable.Seq[SampleCandidate] = _

  override def initialize(aContext: UimaContext) = {
    // NOOP
  }

  override def process(jCas: JCas): Unit = {

    val revision = jCas.revision
    val revisionNumber = revision.getWikipediaRevNumber
    val articleName = revision.getWikipediaArticleName

    sampleCandidates = (for {
      sc <- jCas.sampleCandidates
      s = sc.getSentcence
      sentenceText = s.getCoveredText
      relations = s.relations.map(rToScalaType(_,s))
      values = s.values.map(vToScalaType(_,s))
      timexes = s.timexes.map(tToScalaType(_,s))
      entities = s.entities.map(eToScalaType(_,s))
    } yield SampleCandidate(sentenceText, articleName, Seq(revisionNumber), entities, relations, values, timexes)
    ).toList

  }

}
