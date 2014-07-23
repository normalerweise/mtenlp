package ch.weisenburger.nlp.surface_string_parser


import ch.weisenburger.nlp.reuse.dbpedia.ParserUtils

/**
 * Created by Norman on 09.07.14.
 */
object FormattedNumberParser {

  val dbPediaParserUtilsEN = new ParserUtils

  def parseValue(str: String) = {
    val withoutScaleChars = dbPediaParserUtilsEN.convertLargeNumbers(str)
    val number = dbPediaParserUtilsEN.parse(withoutScaleChars)

    number
  }
}
