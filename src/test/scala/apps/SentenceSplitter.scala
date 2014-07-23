package apps

import ch.weisenburger.uima.FinancialDataSamplePipelineFactory
import ch.weisenburger.uima.annotator.MyStanfordPOSTaggerWrapper
import de.unihd.dbs.uima.types.heideltime

import scala.collection.JavaConversions._
import scala.io.Source

/**
 * Created by Norman on 20.06.14.
 */
object SentenceSplitterApp extends App {

  val text = Source.fromFile("data/broken_sample.txt").mkString

  val testPipeline = FinancialDataSamplePipelineFactory.createTestPipeline(Seq(new MyStanfordPOSTaggerWrapper))

  val jCas = testPipeline.process(text, "")
  val iter = jCas.getAnnotationIndex(heideltime.Sentence.`type`).iterator()
  val sentences = (for(ss <- iter; s = ss.asInstanceOf[heideltime.Sentence])
   yield { s"${ s.getBegin},${s.getEnd},<" + s.getCoveredText + ">" }).toList


 println(sentences.mkString("\n\n"))

}
