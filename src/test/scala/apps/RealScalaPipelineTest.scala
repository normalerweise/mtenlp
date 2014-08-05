package apps

import ch.weisenburger.uima.FinancialDataPipelineFactory

import scala.io.Source

/**
 * Created by Norman on 02.05.14.
 */
object RealScalaPipelineTest extends App {

    val text = Source.fromFile("data/realsample2.txt").mkString

    val pipeline = FinancialDataPipelineFactory.createSampleExctractionScalaCaseClassPipeline
    val result = pipeline.process(text,"http://dbpedia.org/resource/Apple_Inc.",123456, "TEST")

    println(result._1.mkString("\n"))

  println(result._2.mkString("\n"))

  }
