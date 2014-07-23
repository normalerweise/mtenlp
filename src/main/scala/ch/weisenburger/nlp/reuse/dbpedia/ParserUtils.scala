package ch.weisenburger.nlp.reuse.dbpedia

import java.text.{DecimalFormatSymbols, NumberFormat}
import java.util.Locale
import java.util.regex.Pattern

import scala.language.reflectiveCalls

/**
 * Utility functions used by the data parsers.
 */
//TODO test after re-factor
class ParserUtils {
    private val enLocale = new Locale("en")
    private val scales =  ParserUtilsConfig.scalesMap("en")

    // NumberFormat is not thread-safe
    val numberFormat = new ThreadLocal[NumberFormat] {
      override def initialValue = NumberFormat.getNumberInstance(enLocale)
    } 
    
    private val groupingSeparator = DecimalFormatSymbols.getInstance(enLocale).getGroupingSeparator
    
    private val defaultDecimalSeparator = DecimalFormatSymbols.getInstance(enLocale).getDecimalSeparator
    private val decimalSeparatorsRegex = ParserUtilsConfig.decimalSeparators.get("en") match
      {
        case Some(sep) => ("["+sep+"]").r
        case None => ("""\"""+defaultDecimalSeparator).r 
      }


    // TODO: use "\s+" instead of "\s?" between number and scale?
    // TODO: in some Asian languages, digits are not separated by thousands but by ten thousands or so...
    private val regex = ("""(?i)([\D]*)([0-9]+(?:(?:\""" + groupingSeparator + """|\s)[0-9]{3})*)(""" + decimalSeparatorsRegex + """[0-9]+)?\s?\[?\[?(""" + scales.keySet.map(Pattern.quote).mkString("|") + """)\]?\]?(.*)""").r
    
    def parse(str: String): Number = {
      // space is sometimes used as grouping separator
      val cleanedString = decimalSeparatorsRegex.replaceAllIn(str, ""+defaultDecimalSeparator)
      numberFormat.get.parse(cleanedString.replace(' ', groupingSeparator))
    }

    /**
     * Converts large numbers like '100.5 million' to '100500000'
     */
    def convertLargeNumbers(input : String) : String =
    {
        input match
        {
            case regex(begin, integer, fract, scale, end) =>
            {
                val fraction = if(fract != null) fract.substring(1) else ""
                val trailingZeros = "0" * (scales(scale.toLowerCase) - fraction.length)
                val correctedInteger = integer
                  .replace(groupingSeparator.toString,"")
                  .replace(" ","")
                begin + correctedInteger + fraction + trailingZeros + end
            }
            case _ => input
        }
    }
}
