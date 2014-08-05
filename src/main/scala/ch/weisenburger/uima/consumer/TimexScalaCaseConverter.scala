package ch.weisenburger.uima.consumer

import ch.weisenburger.uima.types.distantsupervision.skala.Timex
import ch.weisenburger.uima.util.UimaTypesSugar
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.jcas.JCas

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
class TimexScalaCaseConverter extends JCasAnnotator_ImplBase with UimaTypesSugar {

  import ch.weisenburger.uima.types.distantsupervision.skala.SkalaTypeConversions._

  var timexes: scala.collection.immutable.Seq[Timex] = _

  override def process(jCas: JCas): Unit = {
    timexes = (for {
      timex <- jCas.timexes
    } yield tToScalaType(timex) ).toList
  }

}
