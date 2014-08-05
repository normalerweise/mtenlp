package apps

import ch.weisenburger.uima.FinancialDataPipelineFactory

import scala.io.Source

/**
 * Created by Norman on 02.05.14.
 */
object RealPipelineTest extends App {

    val text = Source.fromFile("data/realsample2.txt").mkString

    val pipeline = FinancialDataPipelineFactory.createSampleExtractionConsolePipeline
    pipeline.process(text,"http://dbpedia.org/resource/Apple_Inc.",123456)

    println(pipeline.stats.map{ case (componentName,stats) =>
      s"""
       |$componentName:
       |  ${stats.map{case (key, value) => s"$key: $value"}.mkString("\n  ")}
      """.stripMargin}.mkString("\n"))

  }
