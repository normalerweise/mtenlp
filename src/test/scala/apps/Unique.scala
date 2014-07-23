package apps

import java.io.FileWriter

import scala.io.Source

/**
 * Created by Norman on 16.07.14.
 */
object Unique extends App {

  val sampleFile = "data/extendedSamplesTestSet.txt"
  val resources = Source.fromFile(sampleFile).getLines().toSeq
  println(resources.size)
  val uniqueResources = resources.toSet
  println(uniqueResources.size)
  val fw = new FileWriter(sampleFile)
  fw.write(uniqueResources.mkString("\n"))
  fw.close
  println("Done")


}
