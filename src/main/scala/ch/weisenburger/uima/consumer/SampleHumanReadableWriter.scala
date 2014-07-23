package ch.weisenburger.uima.consumer


import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
class SampleHumanReadableWriter extends JCasAnnotator_ImplBase with UimaTypesSugar {

  override def process(jCas: JCas): Unit = {

    for {
      sample <- jCas.samples
      sentence = sample.getSentcene
      sEntity = sample.getSentenceSubject
      sRelation = sample.getSentencePredicate
      sValue = sample.getSentenceObject
      sTimex = sample.getSentenceTimex
      qEntity = sample.getQuadSubject
      qRelation = sample.getQuadPredicate
      qValue = sample.getQuadObject
      qTimex = Option(sample.getQuadTimex).getOrElse("?")
    } {
      val es = s"${sEntity.surfaceString(sentence)} (${sEntity.beginRelativeToSentence(sentence)}, ${sEntity.endRelativeToSentence(sentence)})".padTo(qEntity.length, " ").mkString
      val rs = (sRelation match {
        case null => "?"
        case _ => s"${sRelation.surfaceString(sentence)} (${sRelation.beginRelativeToSentence(sentence)}, ${sRelation.endRelativeToSentence(sentence)})"
      }).padTo(qRelation.length, " ").mkString
      val vs = s"(${sValue.beginRelativeToSentence(sentence)}, ${sValue.endRelativeToSentence(sentence)})".padTo(qValue.length, " ").mkString
      val ts = (sTimex match {
        case null => "?"
        case _ => s"(${sTimex.beginRelativeToSentence(sentence)}, ${sTimex.endRelativeToSentence(sentence)})"
      }).padTo(qTimex.length, " ").mkString


      println(
        s"""
          |Sentence: ${sentence.getCoveredText}
          |    Quad: <$qEntity, $qRelation, $qValue, $qTimex>
          |           $es, $rs, $vs, $ts
        """.stripMargin)
    }

  }

}
