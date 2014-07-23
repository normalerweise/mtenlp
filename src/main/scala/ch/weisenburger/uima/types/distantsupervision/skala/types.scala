package ch.weisenburger.uima.types.distantsupervision.skala

import ch.weisenburger.uima.types.distantsupervision
import ch.weisenburger.uima.util.UimaTypesSugar
import de.unihd.dbs.uima.types.heideltime

object SkalaTypeConversions extends UimaTypesSugar {

   val eToScalaType = (e: distantsupervision.NamedEntity, s: heideltime.Sentence) =>
    distantsupervision.skala.Entity(e.getDbpediaURI, e.beginRelativeToSentence(s), e.endRelativeToSentence(s))

   val rToScalaType = (r: distantsupervision.Relation, s: heideltime.Sentence) => r match {
     case null => null
     case r => distantsupervision.skala.Relation(r.getOntologyURI, r.beginRelativeToSentence(s), r.endRelativeToSentence(s))
   }

   val tToScalaType = (t: heideltime.Timex3, s: heideltime.Sentence) => t match {
     case null => null
     case t => distantsupervision.skala.Timex(t.getTimexValue, t.beginRelativeToSentence(s), t.endRelativeToSentence(s))
   }

   val vToScalaType = (v: distantsupervision.Value, s: heideltime.Sentence) =>
    distantsupervision.skala.Value(v.getParsedNumericValue, v.beginRelativeToSentence(s), v.endRelativeToSentence(s))

}

case class Entity(dbpediaResourceUri: String, begin: Int, end: Int)
case class Relation(dbpediaOntologyUri: String, begin: Int, end: Int)
case class Value(parsedNumericValue: String, begin: Int, end: Int)
case class Timex(value: String, begin: Int, end: Int)
case class Quad(entity: String, relation: String, value: String, timex: Option[String])

/** A Sample is a natural language sentence which can be mapped to a known quad in a knowledge base
 *
 * The same sample might occur in several revisions of the same article
 */
case class Sample(sentenceText: String, articleName: String, revisionNumber: Seq[Long], quad: Quad, sEntity: Entity, sRelation: Option[Relation], sValue: Value, sTimex: Option[Timex])

/** A Sample candidate is a sentence which contains components required for a Sample, but
 *  where no matching quad in a knowledge is known
 *
 */
case class SampleCandidate(sentenceText: String, articleName: String, revisionNumber: Seq[Long], entities: Seq[Entity], relations: Seq[Relation], values: Seq[Value], timexes: Seq[Timex])

