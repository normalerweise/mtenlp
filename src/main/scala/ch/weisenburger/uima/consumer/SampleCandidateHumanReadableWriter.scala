package ch.weisenburger.uima.consumer

import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
class SampleCandidateHumanReadableWriter extends JCasAnnotator_ImplBase with UimaTypesSugar {


  override def initialize(aContext: UimaContext) = {
    // NOOP
  }

  override def process(jCas: JCas): Unit = {

    for{
      sc <- jCas.sampleCandidates
      s = sc.getSentcence
      relations = s.relations
      values = s.values
      timexes = s.timexes
      entities = s.entities
    } {
      val strRelations = relations.map(r =>
        s"${r.getOntologyURI} (${r.beginRelativeToSentence(s)}, ${r.endRelativeToSentence(s)})" ).mkString("; ")

      val strValues = values.map(v =>
        s"${v.getParsedNumericValue} (${v.beginRelativeToSentence(s)}, ${v.endRelativeToSentence(s)})").mkString("; ")

      val strTimexes = timexes.filter(t => t.getTimexType == "DATE" ).map( t =>
        s"${t.getTimexValue} (${t.beginRelativeToSentence(s)}, ${t.endRelativeToSentence(s)})").mkString("; ")

      val strEntities = entities.map(e =>
        s"${e.getDbpediaURI} (${e.beginRelativeToSentence(s)}, ${e.endRelativeToSentence(s)})").mkString("; ")


      println(
        s"""
          |Sentence: ${s.getCoveredText}
          |  S:$strEntities
          |  P:$strRelations
          |  O:$strValues
          |  T:$strTimexes
        """.stripMargin)

    }

  }

}
