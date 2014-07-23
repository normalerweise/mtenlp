//package ner
//
//import runtime_analysis.Instrumented
//
//import scala.collection.JavaConversions._
//import com.aliasi.dict.{ExactDictionaryChunker, DictionaryEntry, MapDictionary}
//import com.aliasi.tokenizer.IndoEuropeanTokenizerFactory
//
///**
// * Created by Norman on 11.05.14.
// */
//object LingPipeNer extends App with Instrumented {
//
//  val dictionaryChunker = new ExactDictionaryChunker(loadDictionary,
//    IndoEuropeanTokenizerFactory.INSTANCE, true,true);
//
//
//  private def loadDictionary = {
//    val CHUNK_SCORE = 1.0;
//    val dictionary = new MapDictionary[String]
//
//    val lt = metrics.timer("loadUriSurfaceStringMap")
//    val bt = metrics.timer("buildDictionary")
//
//    val entitiesWithSurfaceStrings = lt.time { Util.loadUriSurfaceStringMap() }
//
//    bt.time {
//      for {
//        entityTuple <- entitiesWithSurfaceStrings
//        entity = entityTuple._2
//        surfaceString <- entity.surfaceStrings
//      } yield dictionary.addEntry( new DictionaryEntry[String](surfaceString,entity.title,CHUNK_SCORE))
//    }
//
//    dictionary
//  }
//
//}
