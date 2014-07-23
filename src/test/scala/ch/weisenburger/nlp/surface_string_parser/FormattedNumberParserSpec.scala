package ch.weisenburger.nlp.surface_string_parser

import org.specs2.mutable._

class FormattedNumberParserSpec extends Specification {

  "A formatted number string" should {
    "be parsed if it contains no clutter" in {
      implicit val text = "7500"
      processAndCheck("7500")
    }

    "be found if it contains one thousands separator" in {
      implicit val text = "7,500"
      processAndCheck("7500")
    }

    "be parsed if it contains multiple thousands separators" in {
      implicit val text = "7,500,00"
      processAndCheck("750000")
    }

    "be parsed if it contains whitespace as thousands separator" in {
      implicit val text = "7 500"
      processAndCheck("7500")
    }

    "be parsed if it contains multiple whitespace thousands separators" in {
      implicit val text = "7 500 000"
      processAndCheck("7500000")
    }

    "be parsed if it contains thousands separators and has double value" in {
      implicit val text = "1,100.2"
      processAndCheck("1100.2")
    }

    "be parsed if it has double value and scale indicator" in {
      implicit val text = "2.2 million"
      processAndCheck("2200000")
    }

    "be parsed if it has double value and scale indicator 2" in {
      implicit val text = "2.2 billion"
      processAndCheck("2200000000")
    }

    "be parsed if it has whitespace thousands separators and scale indicator" in {
      implicit val text = "7 500 million"
      processAndCheck("7500000000")
    }

    "be parsed if it has comma thousands separators and scale indicator" in {
      implicit val text = "7,500 million"
      processAndCheck("7500000000")
    }

    "be parsed if there are special chars between number and scales" in {
      implicit val text = "$12?billion"
      processAndCheck("120000000000")
    }

//
//    "be found if next to char at beginng" in {
//      implicit val text = "with7 500 employees"
//      processAndCheck7500WhitespaceSep
//    }
//
//    "be found if next to char at end" in {
//      implicit val text = "with 7 500employees"
//      processAndCheck7500WhitespaceSep
//    }
//
//    "be found if next to char at beginnig and end" in {
//      implicit val text = "with7 500employees"
//      processAndCheck7500WhitespaceSep
//    }
//
//    "be found solely" in {
//      implicit val text = "7 500"
//      processAndCheck7500WhitespaceSep
//    }
  }


  private def processAndCheck(expStringValue: String)(implicit text: String) = {
    val number = p(text)
    number.toString must beEqualTo(expStringValue)
  }

  // process
  private def p(text: String) = FormattedNumberParser.parseValue(text)

}

