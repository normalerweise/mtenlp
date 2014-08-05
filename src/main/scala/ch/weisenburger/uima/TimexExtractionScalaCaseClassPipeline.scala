package ch.weisenburger.uima

import ch.weisenburger.uima.annotator.MyStanfordPOSTaggerWrapper
import ch.weisenburger.uima.consumer.TimexScalaCaseConverter
import ch.weisenburger.uima.types.distantsupervision
import de.unihd.dbs.uima.annotator.heideltime.HeidelTime
import org.apache.uima.jcas.JCas


/**
 * Created by Norman on 07.07.14.
 */
class TimexExtractionScalaCaseClassPipeline private[uima] (
                                                            private val jCas: JCas,
                                                            private val stanfordPosTagger: MyStanfordPOSTaggerWrapper,
                                                            private val heidelTime: HeidelTime,
                                                            private val timexScalaCaseConverter: TimexScalaCaseConverter
                                                      ) {


  protected val components = Seq(stanfordPosTagger, heidelTime, timexScalaCaseConverter)


  def process(text: String): Seq[distantsupervision.skala.Timex] = try {

    jCas.setDocumentText(text)

    components.foreach(c => c.process(jCas))

    timexScalaCaseConverter.timexes
  } finally {
    jCas.reset
  }

}