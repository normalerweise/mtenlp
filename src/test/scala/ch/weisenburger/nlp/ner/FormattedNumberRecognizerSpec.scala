package ch.weisenburger.nlp.ner

import com.aliasi.chunk.Chunk
import org.specs2.mutable._

class FormattedNumberRecognizerSpec extends Specification {

  "A number (e.g. 7500)" should {
    "be found if surrounded by whitespace" in {
      implicit val text = "with 7500 employees"
      processAndCheck7500
    }

    "be found if next to char at beginng" in {
      implicit val text = "with7500 employees"
      processAndCheck7500
    }

    "be found if next to char at end" in {
      implicit val text = "with 7500employees"
      processAndCheck7500
    }

    "be found if next to char at beginning and end" in {
      implicit val text = "with7500employees"
      processAndCheck7500
    }

    "be found if commas are thousands separators" in {
      implicit val text = "with 7500000 employees"
      val chunks = p(text)
      chunks must have size (1)
      chunkText(chunks.head)(text) must beEqualTo("7500000")
    }

    "be not found if commas are not thousands separators" in {
      implicit val text = "with 7,500,00 employees"
      val chunks = p(text)
      chunks must beEmpty
    }

    "be found if there are special chars between number and scales" in {
      implicit val text = "$12?billion"
      processAndCheck(text, "12?billion")
    }
  }

  "A comma separated number (e.g. 7,500)" should {
    "be found if surrounded by whitespace" in {
      implicit val text = "with 7,500 employees"
      processAndCheck7500CommaSep
    }

    "be found if next to char at beginng" in {
      implicit val text = "with7,500 employees"
      processAndCheck7500CommaSep
    }

    "be found if next to char at end" in {
      implicit val text = "with 7,500employees"
      processAndCheck7500CommaSep
    }

    "be found if next to char at beginning and end" in {
      implicit val text = "with7,500employees"
      processAndCheck7500CommaSep
    }

    "be found if commas are thousands separators" in {
      implicit val text = "with 7,500,000 employees"
      val chunks = p(text)
      chunks must have size (1)
      chunkText(chunks.head)(text) must beEqualTo("7,500,000")
    }

    "be not found if commas are not thousands separators" in {
      implicit val text = "with 7,500,00 employees"
      val chunks = p(text)
      chunks must beEmpty
    }

    "be found solely" in {
      implicit val text = "7,500"
      processAndCheck7500CommaSep
    }

    "be found if followed by a scale indicator" in {
      implicit val text = "with 7,500 million revenue"
      processAndCheck(text, "7,500 million")
    }

  }

  "A whitespace separated number (e.g. 7 500)" should {
    "be found if surrounded by whitespace" in {
      implicit val text = "with 7 500 employees"
      processAndCheck7500WhitespaceSep
    }

    "be found if next to char at beginng" in {
      implicit val text = "with7 500 employees"
      processAndCheck7500WhitespaceSep
    }

    "be found if next to char at end" in {
      implicit val text = "with 7 500employees"
      processAndCheck7500WhitespaceSep
    }

    "be found if next to char at beginnig and end" in {
      implicit val text = "with7 500employees"
      processAndCheck7500WhitespaceSep
    }

    "be found solely" in {
      implicit val text = "7 500"
      processAndCheck7500WhitespaceSep
    }

    "be found if followed by a scale indicator" in {
      implicit val text = "with 7 500 million revenue"
      processAndCheck(text, "7 500 million")
    }

  }

  "A  double number (e.g. 2.2)" should {
    "be found if surrounded by whitespace" in {
      implicit val text = "with 2.2 employees"
      processAndCheck(text, "2.2")
    }

    "be found if followed by a scale indicator" in {
      implicit val text = "with 2.2 million employees"
      processAndCheck(text, "2.2 million")
    }

    "be found if followed by a scale indicator" in {
      implicit val text = "with 2.2 billion employees"
      processAndCheck(text, "2.2 billion")
    }

    "be found if combined with thousands separators" in {
      implicit val text = "with 1,100.2 bla"
      processAndCheck(text, "1,100.2")
    }
  }


  private def processAndCheck7500(implicit text: String) = {
    processAndCheck(text, "7500")
  }

  private def processAndCheck7500CommaSep(implicit text: String) = {
    processAndCheck(text, "7,500")
  }

  private def processAndCheck7500WhitespaceSep(implicit text: String) = {
    processAndCheck(text, "7 500")
  }

  private def processAndCheck(text: String, stringValue: String) = {
    val chunks = p(text)
    chunks must have size (1)
    chunkText(chunks.head)(text) must beEqualTo(stringValue)
  }


  // process
  private def p(text: String) = FormattedNumberRecognizer.chunkAll(text)

  private def chunkText(chunk: Chunk)(implicit text: String) =
    FormattedNumberRecognizer.getSurfaceString(chunk)

}

