package ch.weisenburger.uima.util

import scala.collection.JavaConversions._
import org.apache.uima.cas.FSIterator
import org.apache.uima.jcas.{JCas, tcas}
import de.unihd.dbs.uima.types.heideltime
import ch.weisenburger.uima.reuse.uimafit.JCasUtil
import ch.weisenburger.uima.types.distantsupervision


/**
 * Created by Norman on 08.07.14.
 */
trait UimaTypesSugar {

  implicit class SugaredNamedEntity(relation: distantsupervision.NamedEntity)
    extends SugaredUimaType(relation)

  implicit class SugaredRelation(relation: distantsupervision.Relation)
    extends SugaredUimaType(relation)

  implicit class SugaredValue(value: distantsupervision.Value)
    extends SugaredUimaType(value)

  implicit class SugaredTimex3(timex: heideltime.Timex3)
    extends SugaredUimaType(timex)


  implicit class SugaredSentence(sentence: heideltime.Sentence) {

    val relations: List[distantsupervision.Relation] =
      JCasUtil.selectCovered(classOf[distantsupervision.Relation], sentence).toList

    val values: List[distantsupervision.Value] =
      JCasUtil.selectCovered(classOf[distantsupervision.Value], sentence).toList

    val timexes: List[heideltime.Timex3] =
      JCasUtil.selectCovered(classOf[heideltime.Timex3], sentence).iterator.toList

    val entities: List[distantsupervision.NamedEntity] =
      JCasUtil.selectCovered(classOf[distantsupervision.NamedEntity], sentence).iterator.toList

    def nonOverlappingRelations = filterOverlapping(relations)

    def nonOverlappingValues = filterOverlapping(values)

    def nonOverlappingTimexes = filterOverlapping(timexes)

    def nonOverlappingEntities = filterOverlapping(entities)


    private def filterOverlapping[T <: tcas.Annotation](annotations: List[T]): List[T] = {
      def checkOverlap(annotation: T, successors: List[T]): List[T] = {
        successors match {
          case next :: tail => {
            assert(annotation.getBegin <= next.getBegin)
            if (annotation.getEnd < next.getBegin) {
              annotation :: checkOverlap(next, tail)
            } else if (next.getBegin >= annotation.getBegin && next.getEnd <= annotation.getEnd) {
              checkOverlap(annotation, tail)
            } else if (annotation.getBegin == next.getBegin && next.getEnd > annotation.getEnd) {
              checkOverlap(next, tail)
            } else {
              annotation :: checkOverlap(next, tail)
            }
          }
          case Nil => List(annotation)
        }
      }
      if (annotations.nonEmpty) {
        val sorted = annotations.sortBy(_.getBegin)
        checkOverlap(sorted.head, sorted.tail)
      } else {
        annotations
      }
    }

  }

  implicit class SugaredSampleCandidate(sampleCandidate: distantsupervision.SampleCandidate) {

    def relations = sampleCandidate.getSentcence.relations

    def values = sampleCandidate.getSentcence.values

    def timexes = sampleCandidate.getSentcence.timexes

    def entities = sampleCandidate.getSentcence.entities

  }


  implicit class SugaredJCas(jCas: JCas) {

    def sentences = jCas.getAnnotationIndex(heideltime.Sentence.`type`)
      .iterator().asInstanceOf[FSIterator[heideltime.Sentence]]

    def samples = jCas.getAnnotationIndex(distantsupervision.Sample.`type`)
      .iterator().asInstanceOf[FSIterator[distantsupervision.Sample]]

    def sampleCandidates = jCas.getAnnotationIndex(distantsupervision.SampleCandidate.`type`)
      .iterator().asInstanceOf[FSIterator[distantsupervision.SampleCandidate]]

    // There must be exactly one revision
    def revision: distantsupervision.Revision = jCas.getAnnotationIndex(distantsupervision.Revision.`type`)
      .iterator().asInstanceOf[FSIterator[distantsupervision.Revision]].next
  }

}

abstract class SpecialSentence(var sentence: heideltime.Sentence) {

}

abstract class SugaredUimaType(var uimaType: tcas.Annotation) {

  lazy val length = uimaType.getEnd - uimaType.getBegin

  def beginRelativeToSentence(s: heideltime.Sentence) =
    uimaType.getBegin - s.getBegin

  def endRelativeToSentence(s: heideltime.Sentence) =
    beginRelativeToSentence(s) + length

  def surfaceString(s: heideltime.Sentence) =
    s.getCoveredText.substring(beginRelativeToSentence(s), endRelativeToSentence(s))

  
}
