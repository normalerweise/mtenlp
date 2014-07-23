package ch.weisenburger.deprecated_ner

import java.io.{BufferedWriter, FileWriter}

import scala.collection.mutable
import scala.xml.{Elem, PrettyPrinter, XML}

/**
 * Created by Norman on 11.05.14.
 */
object Util {

  case class CompanyWithSurfaceStings(title: String, uri: String, surfaceStrings: mutable.Set[String])

  val uriSurfaceStringXMLPath = "data/companyUrisWithSurfaceString.xml"

  def getLastUriComponent(uri: String) = {
    val title = uri.split('/').last
    assert(title != null)
    title
  }


  def saveUriSurfaceStringXML(node: Elem) {
    saveXML(uriSurfaceStringXMLPath, node)
  }

  def saveXML(path: String, node: Elem) {
    val formatter = new PrettyPrinter(120, 2)

    val sb = new StringBuilder
    formatter.format(node, null, sb)
    val writer = new BufferedWriter(new FileWriter(path))
    writer.append(sb)
    writer.close
  }


  def loadUriSurfaceStringMap(): Map[String, CompanyWithSurfaceStings] = {
    val rootNode = XML.loadFile(uriSurfaceStringXMLPath)
    buildSurfaceStringMap(rootNode)
  }

  def loadUriSurfaceStringMapFromJar(): Map[String, CompanyWithSurfaceStings] = {
    val is = getClass.getResourceAsStream("companyUrisWithSurfaceString.xml")
    val rootNode = XML.load(is)
    buildSurfaceStringMap(rootNode)
  }

  private def buildSurfaceStringMap(rootNode: Elem) = {
    val companySeq = (rootNode \ "company").map {
      companyNode =>
        val surfStrings = (companyNode \ "surfaceString").map(element => element.text)
        val company = CompanyWithSurfaceStings(
          (companyNode \ "@titel").text,
          (companyNode \ "@uri").text,
          collection.mutable.Set(surfStrings: _*))
        (company.title, company)
    }

    companySeq.toMap
  }

  def saveUriSurfaceStringMap(companies: Map[String, CompanyWithSurfaceStings]) = {
    val companyNERModelAsXML = companies.toSeq.map {
      case (_, company) =>
        <company uri={company.uri} titel={getLastUriComponent(company.uri)}>
          {for (surfaceString <- company.surfaceStrings) yield <surfaceString>{surfaceString}</surfaceString>}
        </company>
    }
    val rootNode = <companies></companies>.copy(child = companyNERModelAsXML)
    saveUriSurfaceStringXML(rootNode)
  }


  def saveSamplesAsXML(samples: Seq[Sample], path: String) = {
    val samplesAsXML = samples.toSeq.map { sample =>
        <sample subject={sample.entityUri} predicate={sample.relationUri} object={sample.value} subjectStartPos={sample.entityStartPos.toString} subjectEndPos={sample.entityEndPos.toString} objectStartPos={sample.valueStartPos.toString} objectEndPos={sample.valueEndPos.toString} timex={sample.timex} revision={sample.revision} pageId={sample.pageId}>
          { sample.sentence }
        </sample>
    }
    val rootNode = <samples></samples>.copy(child = samplesAsXML)
    saveXML(path, rootNode)
  }

}
