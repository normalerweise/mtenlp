package ch.weisenburger.deprecated_ner

import java.io.{File, FileWriter}

import scala.io.{Codec, Source}

/**
 * Created by Norman on 11.05.14.
 */
object FileUtil {

  implicit val codec = Codec.UTF8

  def cacheFilePathForQueryId(queryId: String) = s"data/${queryId}Uris.txt"

  def writeOneElementPerLine(filePath: String, list: Seq[String]) =
    writeFile(filePath, list.mkString("\n"))

  def readFileOneElementPerLine(filePath: String): List[String] =
    readFileFromPathOrElse(filePath, {
      source => source.getLines().toList
    }, {
      List.empty[String]
    })

  def readMaybeFileOneElementPerLine(filePath: String): Option[List[String]] =
    readFileFromPathOrElse(filePath, {
      source => Some(source.getLines().toList)
    }, {
      None
    })

  private def readFileFromPathOrElse[E](path: String, exists: Source => E, default: => E) = {
    val f = new File(path)
    if (f.exists && !f.isDirectory) exists(Source.fromFile(f))
    else default
  }

  private def writeFile(path: String, content: String) = {
    val writer = new FileWriter(new File(path))
    writer.write(content)
    writer.close()
  }


  def ensureExists(path: String) = {
    val f = new File(path)
    if (!f.exists()) {
      f.getParentFile.mkdirs
    }
    f
  }

}
