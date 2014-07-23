package ch.weisenburger.dataset_statistics

import ch.weisenburger.deprecated_ner.Util

/**
 * Created by Norman on 12.05.14.
 */
object SurfaceStringLengthStats extends App{

  val entitiesWithSurfaceStrings = Util.loadUriSurfaceStringMap()
  val surfaceStrings = entitiesWithSurfaceStrings.flatMap( _._2.surfaceStrings )
  val groupedByLength = surfaceStrings.groupBy(_.length).map(e =>(e._1, e._2.size))
  val sorted = groupedByLength.toList.sortBy(_._1)
  println("Length, Occurences")
  println(sorted.mkString("\n"))


}
