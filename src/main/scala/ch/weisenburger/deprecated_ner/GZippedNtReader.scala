package ch.weisenburger.deprecated_ner

import java.io._
import java.util.zip.GZIPInputStream

import scala.io.Source

/**
 * Created by Norman on 14.04.14.
 */
object GZippedNtReader {
  import ch.weisenburger.deprecated_ner.FileUtil._

  case class MTriple(subj: String, pred: String, obj: String)

  case class CachingFailedException(message: String) extends Exception(message)

  private val cacheId = "data/airpedia-dbpedia-companiesUris.txt"

  //  def readAirpediaDbpediaCompaniesWithConfidenceGe9(path: String) = for {
  //      line <- getSource(path).getLines
  //      triple = line.split("\\s+")
  //      t = MTriple(triple(0), triple(1), triple(2))
  //      result = t.subj if( t.obj == "<http://dbpedia.org/ontology/Company>" && (
  //          t.pred == "<http://airpedia.org/ontology/type_with_conf#10>" ||
  //          t.pred == "<http://airpedia.org/ontology/type_with_conf#9>" ))
  //     } yield result


  def readOrCacheAirpediaDbpediaCompaniesWithConfidenceGe9(path: String): Seq[String] = readMaybeFileOneElementPerLine(cacheId) match {
    case Some(result) => result
    case None =>
      val writer = new BufferedWriter(new FileWriter(new File(cacheId)))
      val gzIter = gZippedSource(path).getLines
      while (gzIter.hasNext) {
        val triple = gzIter.next.split("\\s+")
        val t = MTriple(triple(0), triple(1), triple(2))
        if (t.obj == "<http://dbpedia.org/ontology/Company>" && (
            t.pred == "<http://airpedia.org/ontology/type_with_conf#10>" ||
            t.pred == "<http://airpedia.org/ontology/type_with_conf#9>")) {
          writer.write(t.subj.substring(1,t.subj.length - 1))
          writer.write("\n")
        }
      }
      writer.close
      readMaybeFileOneElementPerLine(cacheId) match {
        case Some(result) => result
        case None => throw new FileNotFoundException(cacheId)
      }
  }


  def gZippedSource(path: String) = Source.fromInputStream(
    new GZIPInputStream(
      new FileInputStream(new File(path))))


}
