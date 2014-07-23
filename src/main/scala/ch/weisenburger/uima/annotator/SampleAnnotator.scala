package ch.weisenburger.uima.annotator

import ch.weisenburger.nlp.ner.KBFinancialDataRelationTripleMatcher
import ch.weisenburger.uima.Statistics
import ch.weisenburger.uima.types.distantsupervision
import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._


/**
 * Created by Norman on 07.07.14.
 */
class SampleAnnotator extends JCasAnnotator_ImplBase
  with Statistics with UimaTypesSugar {

  private var processed = 0
  private var matched = 0
  private var dropped = 0
  private var candidates = 0

  override def initialize(aContext: UimaContext) = {
    // force init
    val init = KBFinancialDataRelationTripleMatcher.entityValueLookup
  }


  override def process(jCas: JCas): Unit = {

    for (sentence <- jCas.sentences) {
      val entities = sentence.entities
      val relations = sentence.relations
      val values = sentence.values


      if (entities.size >= 1 && values.size >= 1) {
        val matchingQuads = for {
          entity <- entities
          entityUri = entity.getDbpediaURI
          value <- values
          numericValue = value.getParsedNumericValue
          matchedQuad <- KBFinancialDataRelationTripleMatcher.findEntityValueMatch(entityUri, numericValue)
        } yield {
          val sample = new distantsupervision.Sample(jCas)
          sample.setQuadSubject(matchedQuad.subject)
          sample.setQuadPredicate(matchedQuad.predicate)
          sample.setQuadObject(matchedQuad.value)
          sample.setQuadTimex(matchedQuad.timex.getOrElse(null))

          sample.setSentcene(sentence)
          sample.setSentenceSubject(entity)
          sample.setSentenceObject(value)

          val sentencePredicate = relations.find(_.getOntologyURI == matchedQuad.predicate)
          sample.setSentencePredicate(sentencePredicate.getOrElse(null))

          val timexes = sentence.timexes
          val sentenceTimex = timexes.find(_.getTimexValue.startsWith(matchedQuad.timex.getOrElse("@@@@")))
          sample.setSentenceTimex(sentenceTimex.getOrElse(null))

          sample.addToIndexes()
          sample
        }


        if (matchingQuads.size >= 1) {
          matched += 1
        } else if( relations.size >= 1) {
        // These are interesting -> check why we do not have a match
          val sampleCandidate = new distantsupervision.SampleCandidate(jCas)
          sampleCandidate.setSentcence(sentence)
          sampleCandidate.addToIndexes
          candidates += 1
        } else {
          dropped += 1
        }

      } else {
        dropped += 1
      }
      processed += 1
    }
  }

  def stats = Map(
    "processed" -> processed.toString,
    "matched" -> matched.toString,
    "candidates" -> candidates.toString,
    "dropped" -> dropped.toString,
    "dropRatio" -> (dropped.toDouble / processed).toString)

}
