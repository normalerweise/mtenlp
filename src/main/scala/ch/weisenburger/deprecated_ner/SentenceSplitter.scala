package ch.weisenburger.deprecated_ner

import java.io.StringReader

import edu.stanford.nlp.ling.{Word, CoreLabel}
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory
import edu.stanford.nlp.process.{TokenizerFactory, CoreLabelTokenFactory, DocumentPreprocessor, PTBTokenizer}
import edu.stanford.nlp.tagger.maxent.MaxentTagger

import scala.collection.JavaConversions._
;

/**
 * Created by Norman on 08.05.14.
 */
object SentenceSplitter {

// val tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory,
//    "normalizeParentheses=false,normalizeOtherBrackets=false,invertible=true");

//
//  //  val TOKENIZER_FACTORY = new IndoEuropeanTokenizerFactory()
////  val SENTENCE_MODEL  = new MedlineSentenceModel()
////  val sentenceChunker = new SentenceChunker(TOKENIZER_FACTORY, SENTENCE_MODEL)
////
//  def split(text: String): Seq[String] = {
//      val dp = new DocumentPreprocessor(new StringReader(text));
//      dp.setTokenizerFactory(tokenizerFactory)
//
//      val res = (for {
//        s <- dp
//        sc = s.asInstanceOf[java.util.List[CoreLabel]]
//      } yield
//        edu.stanford.nlp.util.StringUtils.joinWithOriginalWhiteSpace(sc)).toSeq
//      res
////    val chunks = sentenceChunker.chunk(text)
////    chunks.chunkSet.map { chunk => text.substring(chunk.start,chunk.end ) }
//     //Set.empty
//  }

  val fac: TokenizerFactory[Word] = PTBTokenizerFactory.newTokenizerFactory
  fac.setOptions("ptb3Escaping=false,untokenizable=noneKeep")

  def split(text: String): Seq[String] = {
    MaxentTagger.tokenizeText(new StringReader(text), fac).map("<" + _.mkString(" ") + ">")
  }

}
