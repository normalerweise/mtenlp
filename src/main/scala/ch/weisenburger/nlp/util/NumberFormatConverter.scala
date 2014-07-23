package ch.weisenburger.nlp.util


import ch.weisenburger.nlp.reuse.dbpedia.ParserUtils

/**
 * Created by Norman on 10.07.14.
 */
object NumberFormatConverter {

  val numberFormat = (new ParserUtils).numberFormat

  def convertToFormattedNumberString(anyNumericFormatString: String) = {
   val number = numberFormat.get.parse(anyNumericFormatString)

   number.toString
  }

}
