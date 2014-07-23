package ch.weisenburger.nlp.ner

import java.util.regex.Pattern

import ch.weisenburger.nlp.reuse.dbpedia.ParserUtilsConfig
import com.aliasi.chunk.{Chunk, ChunkFactory, RegExChunker}

import scala.collection.JavaConversions._

/**
 * Created by Norman on 08.07.14.
 */
object FormattedNumberRecognizer {
  private val CHUNK_SCORE = 0.0

  private val chunker = synchronized { Seq(
    createFormattedNumberChunker1
  ) }

  // as we excluded references like [1] we match one character more
  // than the actual number -> correct this
  private val correctStartEnd = (c: Chunk) => {
    ChunkFactory.createChunk(c.start + 1, c.end - 1, c.`type` ,c.score)
  }


  // invoke all chunkers and merge the results
  def chunkAll(text: String) = {
    chunker.map(c => c.chunk(text).chunkSet.toSet)
      .fold(Set.empty[Chunk]){
        case (s1, s2) => s1 ++ s2
      }
  }


  def getSurfaceString(formattedNumberChunk: Chunk)(implicit text: String) = {
    text.substring(formattedNumberChunk.start, formattedNumberChunk.end)
  }


  private def createFormattedNumberChunker1 = {
    //val regex = "(?:[^\\[,\\d])((?:\\d{1,2}(?:[, ]\\d{3})+)|(?:\\d+))(?:[^,\\d\\]])"
    //val regex = """(?<!,|\d|\[)((?:\d{1,2}(?:[, ]\d{3})+)|(?:\d+))(?:\.\d{1,3})?(?!,|\d|\])(\smillion|\sbillion)?"""
    // reuse scales already defined in DBpedia extraction Framework
    val scaleIndicators = ParserUtilsConfig.scalesMap("en").keySet.map(Pattern.quote).mkString("|")
    val regex = """(?<!,|\d|\[)(\d{1,2}(?:[, ]\d{3})+|\d+)(\.\d{1,3})?(?!,\d|\d|\])(?:[\s\?](""" + scaleIndicators + """))?"""
    val chunkType = "formattedNumber"

    new RegExChunker(regex,chunkType, CHUNK_SCORE)
  }

}


