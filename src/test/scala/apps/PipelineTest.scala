package apps

import ch.weisenburger.uima.FinancialDataPipelineFactory

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.io.Source
import scala.util.{Failure, Success}

/**
 * Created by Norman on 07.07.14.
 */
object PipelineTest extends App {

  val text = Source.fromFile("data/sample.txt").mkString

  val pipeline1 = Future { FinancialDataPipelineFactory.createSampleExtractionConsolePipeline }
  val pipeline2 = Future { FinancialDataPipelineFactory.createSampleExtractionConsolePipeline }

  pipeline1.onComplete {
    case Success(pipeline) =>
      pipeline.process(text,"ACME",123456)
      println("Finished pipe 1")
    case Failure(t) => throw t
  }

  pipeline2.onComplete {
    case Success(pipeline) =>
      pipeline.process(text,"ACME",123456)
      println("Finished pipe 2")
    case Failure(t) => throw t
  }

  val sync = Future.sequence(Seq(pipeline1, pipeline2))

  Await.result(sync, 10 minutes)

}
