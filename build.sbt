name := "mtner"

version := "1.0-SNAPSHOT"

organization := "ch.weisenburger"

scalaVersion := "2.10.3"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

libraryDependencies ++= Seq(
  // crosscutting concerns: logging, config, runtime...
  "org.slf4j" % "slf4j-api" % "1.7.7",
  "org.slf4j" % "jcl-over-slf4j" % "1.7.7", // redirect the apache http lib logging to slf4j
  "nl.grons" %% "metrics-scala" % "3.1.1",
  "com.typesafe" % "config" % "1.2.1",
  // SPARQL
  "org.apache.jena" % "jena-arq" % "2.11.2"
    exclude("log4j", "log4j") exclude("org.slf4j", "slf4j-log4j12") exclude("commons-logging", "commons-logging"),
  // NLP
  "com.aliasi" % "lingpipe" % "4.1.0",
  "edu.stanford.nlp" % "stanford-corenlp" % "3.3.1",
  "de.unihd.dbs" % "heideltime" % "1.7",
  // testing
  "org.specs2" %% "specs2" % "2.3.13" % "test",
  // use logback as logger framework whilst running tests
  "ch.qos.logback" % "logback-classic" % "1.1.2" % "test"
)

net.virtualvoid.sbt.graph.Plugin.graphSettings