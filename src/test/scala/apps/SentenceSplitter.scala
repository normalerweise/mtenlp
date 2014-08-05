package apps

import ch.weisenburger.uima.FinancialDataPipelineFactory
import ch.weisenburger.uima.annotator.MyStanfordPOSTaggerWrapper
import de.unihd.dbs.uima.annotator.stanfordtagger.StanfordPOSTaggerWrapper
import de.unihd.dbs.uima.types.heideltime

import scala.collection.JavaConversions._
import scala.io.Source

/**
 * Created by Norman on 20.06.14.
 */
object SentenceSplitterApp extends App {

  val text = Source.fromFile("data/broken_sample.txt").mkString

  val testPipeline = FinancialDataPipelineFactory.createSampleExtractionTestPipeline(Seq(new StanfordPOSTaggerWrapper))

  val jCas = testPipeline.process(text, "")
  val iter = jCas.getAnnotationIndex(heideltime.Sentence.`type`).iterator()
  val sentences = (for {
    (ss, idx) <- iter.zipWithIndex
    s = ss.asInstanceOf[heideltime.Sentence]
  } yield {
    s"Sentence $idx (${s.getBegin},${s.getEnd}): \n<" + s.getCoveredText + ">"
  }).toList


 println(sentences.mkString("\n\n"))

}
