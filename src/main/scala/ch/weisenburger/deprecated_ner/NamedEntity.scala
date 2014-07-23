package ch.weisenburger.deprecated_ner

/**
 * Created by Norman on 16.04.14.
 */
case class NamedEntity(title: String, titleLemma: String, uri: String, surfaceStrings: Seq[String], occurences: List[String] )
