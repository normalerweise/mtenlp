package ch.weisenburger.deprecated_ner

import java.io.File

import scala.io.Source
import scala.xml.pull.{EvElemStart, EvText, XMLEventReader}

/**
 * Created by Norman on 11.05.14.
 */
object ProcessedDumpReader extends App {

  case class SurfaceString(pageTitleWithWhitespaceReplacedByUnderscore: String, surfaceString: String)

  val wikiTextFilesFilter = (f: File) => {
      val name = f.getName
      val extension = name.substring(name.lastIndexOf(".") + 1, name.length());
      f.isFile && extension == "wikitext"
  }

  val companies = Util.loadUriSurfaceStringMap()

  val files = getRecursiveListOfFiles(new File("resources/simones_xmls")).filter(wikiTextFilesFilter(_))
  files.foreach {
    f =>
      val xml = new XMLEventReader(Source.fromFile(f, "UTF-8"))
      val surfaceStrings = extractSurfaceStringsFromPreprocessedWkiDumpXML(xml)
      mergeSurfaceStrings(companies, surfaceStrings)
      println(s"processed file ${f.getName}")
  }
  Util.saveUriSurfaceStringMap(companies)


  def extractSurfaceStringsFromPreprocessedWkiDumpXML(xml: XMLEventReader) = {
    def loop(surfaceStrings: Seq[SurfaceString]): Seq[SurfaceString] = {
      while (xml.hasNext) {
        xml.next match {
          case EvElemStart(_, "terms", _, _) =>
            val newStrings = processTerms(xml)
            return loop(surfaceStrings ++ newStrings)
          case _ => //noop
        }
      }
      surfaceStrings
    }
    loop(Seq.empty)
  }

  private def mergeSurfaceStrings(companies: Map[String, Util.CompanyWithSurfaceStings], surfaceStrings: Seq[SurfaceString]) = {
    surfaceStrings.foreach {
      surfaceString =>
        companies.get(surfaceString.pageTitleWithWhitespaceReplacedByUnderscore) match {
          case Some(company) =>
            val res = company.surfaceStrings.add(surfaceString.surfaceString)
            if(res == true) println(s"added ${surfaceString.surfaceString} to ${company.title} surface strings")
          case None => //noop
        }
    }
  }

  private def processTerms(xml: XMLEventReader) = xml.next match {
    case EvText(text) => extractSurfaceStringsByPage(text)
    case _ => throw new Exception("unexpected xml event")
  }


  private def extractSurfaceStringsByPage(text: String) = {
    val tokens = text.split("\n").toSeq
    val splitted = tokens.map(tokenline => tokenline.split("\t"))

    splitted.filter(splittedLine => splittedLine.length == 3 && splittedLine(0) != "") // links only
      .map(splittedLine => SurfaceString(splittedLine(0), undoWhitespaceReplacement(splittedLine(1)).trim))
  }

  private def undoWhitespaceReplacement(s: String) = s.replaceAll("_", " ")


  def getRecursiveListOfFiles(dir: File): Array[File] = {
    val these = dir.listFiles
    these ++ these.filter(_.isDirectory).flatMap(getRecursiveListOfFiles)
  }

}
