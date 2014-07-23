package ch.weisenburger.nlp.util

import org.specs2.mutable._


/**
 * Created by Norman on 10.07.14.
 */
class NumberFormatConverterSpec extends Specification {

  "A double String which is an integer if formatted" should {
    "be formatted like a FormattedNumberString" in {
      implicit val text = "7.5E9"
      processAndCheck("7500000000")
    }
  }

  "A double String " should {
    "be formatted like a FormattedNumberString" in {
      implicit val text = "453.123"
      processAndCheck("453.123")
    }
  }

  "A integer String" should {
    "be formatted like a FormattedNumberString" in {
      implicit val text = "7500"
      processAndCheck("7500")
    }
  }

  private def processAndCheck(expStringValue: String)(implicit doubleString: String) = {
    val number = p(doubleString)
    number.toString must beEqualTo(expStringValue)
  }

  // process
  private def p(doubleString: String) = NumberFormatConverter.convertToFormattedNumberString(doubleString)

}
