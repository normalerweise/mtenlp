package apps

import ch.weisenburger.deprecated_ner.LingPipeTrainingGenerator

/**
 * Created by Norman on 02.05.14.
 */
object Playground extends App {

  //val numbers = Seq("1.0E9","1.0E10","1.0E11","1.1E9","1.01E10","1.001E11","1.01E9","1.001E10","1.0001E11")
  val text = "Apple worldwide annual revenue in 2010 totaled $65 billion, growing to $156 billion in 2012."
  //val text = "Despite Jobs' absence, Apple\nrecorded its best non-holiday quarter (Q1 FY 2009) during the recession with a\nrevenue of $8.16 billion and a profit of $1.21 billion.\n\nAfter years of speculation and multiple rumored \"leaks\" Apple announced a large\nscreen, tablet-like media device known as the iPad on January 27, 2010. The\niPad runs the same touch based operating system that the iPhone uses and many\nof the same iPhone apps are compatible with the iPad. This gave the iPad a\nlarge app catalog on launch even with very little development time before the\nrelease. Later that year on April 3, 2010, the iPad was launched in the US and\nsold more than 300,000 units on that day and reaching 500,000 by the end of the\nfirst week. In May of the same year, Apple's market cap exceeded that of\ncompetitor Microsoft for the first time since 1989.\n\nApple released the fourth generation iPhone, which introduced video calling,\nmultitasking, and a new uninsulated stainless steel design, which acts as the\nphone's antenna."


  LingPipeTrainingGenerator.init(List("http://dbpedia.org/ontology/revenue"))
  val result = LingPipeTrainingGenerator.findSamplesInRevision(text,"dummy","dummy")

  println(result.mkString("\n"))

}
