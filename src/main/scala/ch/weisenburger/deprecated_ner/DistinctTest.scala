package ch.weisenburger.deprecated_ner


/**
 * Created by Norman on 12.05.14.
 */
object DistinctTest extends App {

  val a = Sample("a", "a", 1, 2, "a", 1, 1, "1", "aaddsfdfsasd", "sdf", "1")
  val b = Sample("a", "a", 1, 2, "a", 1, 1, "1", "aadsdfsasd", "sdf", "1")
  val c = Sample("a", "a", 1, 2, "a", 1, 1, "1", "adadsasd", "sdf", "1")

  val samples =  List(
      a,b,c
  )

  val distinct = samples.distinct

  println(samples.size)
  println(distinct.size)
  println(a == b)
  println(a equals b)

}
