package ch.weisenburger.dataset_statistics

import java.io.{FileWriter, BufferedWriter}
import java.text.NumberFormat
import java.util.Locale

import ch.weisenburger.crosscutting_concerns.LibConfig
import ch.weisenburger.deprecated_ner.FileUtil
import ch.weisenburger.sparql.{SparqlQueryExecutor, LargeQuery}

import scala.util.Random

/**
 * Created by Norman on 28.07.14.
 */
object EvaluationSetRandomSampleGen extends App with LibConfig {
  val nf = NumberFormat.getInstance(Locale.ENGLISH);
  val relationNames = Seq("revenue", "equity", "assets", "operatingIncome", "numberOfEmployees", "netIncome")
//    val relationName = "equity"
//    val relationName = "assets"
//    val relationName = "operatingIncome"
//    val relationName = "numberOfEmployees"
//    val relationName = "netIncome"
  for(relationName <- relationNames) {
  val sampleSize = 1000
  val relationDBPediaOntologyURI = s"http://dbpedia.org/ontology/$relationName"

  val knownQuadsQuery = LargeQuery("sampleQuadQuery", tripleStoreURL + "/company/sparql", (limit: Int, offset: Int) =>
    s"""
       |PREFIX dbpedia: <http://dbpedia.org/ontology/>
       |
       |SELECT ?s ?p ?o ?d ?source
       |WHERE {
       |  BIND(<$relationDBPediaOntologyURI> as ?p)
       |  ?sp <http://weisenburger.ch/singletonPropertyOf> ?p .
       |  ?s ?sp ?o .
       |  ?sp <http://www.w3.org/ns/prov#wasDerivedFrom> ?source
       |  OPTIONAL { ?sp <http://weisenburger.ch/fromDate> ?d } .
       |
       |}
       |order by ?s ?o ?d
       |OFFSET ${offset}
       |LIMIT ${limit}
      """.stripMargin)

  val queryResult = SparqlQueryExecutor.executeResAsSolution(knownQuadsQuery)
    .map( res => (res.getResource("s"), res.getResource("p"), res.getLiteral("o"), Option(res.getLiteral("d")), res.getResource("source")) )

  val headerLine = s"Got ${queryResult.size} n-tuples \n shuffling..."
  println(headerLine)
  val sample = Random.shuffle(queryResult).take(sampleSize).map { case (s,p,o,t,source) =>
    val tRes = t match {
      case Some(l) => l.getString
      case None => "?"
    }
    s"<$s, $p, $o, $tRes>;${nf.format(o.getString.toDouble)};$tRes;" + "=HYPERLINK(\"" + source + "\")"
  }
  println("write sample file")


  val outputFileWriter = new BufferedWriter(new FileWriter(FileUtil.ensureExists(s"data/evaluation/$relationName-sample.txt")))

  outputFileWriter.write(headerLine + s"\n Sample has size: $sampleSize\n" + sample.mkString("\n"))
  outputFileWriter.close
  }
}
