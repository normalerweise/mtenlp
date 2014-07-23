package ch.weisenburger.uima.annotator;


import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import edu.stanford.nlp.ling.HasOffset;
import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import de.unihd.dbs.uima.annotator.heideltime.utilities.Logger;
import de.unihd.dbs.uima.types.heideltime.Sentence;
import de.unihd.dbs.uima.types.heideltime.Token;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.Word;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.tagger.maxent.TaggerConfig;
import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;

/**
 * @author Norman Weisenburger
 *
 */
public class MyStanfordPOSTaggerWrapper extends JCasAnnotator_ImplBase {

    // definitions of what names these parameters have in the wrapper's descriptor file
    public static final String PARAM_MODEL_PATH = "model_path";
    public static final String PARAM_CONFIG_PATH = "config_path";
    public static final String PARAM_ANNOTATE_TOKENS = "annotate_tokens";
    public static final String PARAM_ANNOTATE_SENTENCES = "annotate_sentences";
    public static final String PARAM_ANNOTATE_PARTOFSPEECH = "annotate_partofspeech";

    // switches for annotation parameters
    private String model_path;
    private String config_path;
    private Boolean annotate_tokens = false;
    private Boolean annotate_sentences = false;
    private Boolean annotate_partofspeech = false;

    // Maximum Entropy Tagger from the Stanford POS Tagger
    private MaxentTagger mt;
    private TokenizerFactory<Word> tokenizerFactory;

    /**
     * initialization method where we fill configuration values and check some prerequisites
     */
    public void initialize(UimaContext aContext) {
        // get configuration from the descriptor
        annotate_tokens = (Boolean) aContext.getConfigParameterValue(PARAM_ANNOTATE_TOKENS);
        annotate_sentences = (Boolean) aContext.getConfigParameterValue(PARAM_ANNOTATE_SENTENCES);
        annotate_partofspeech = (Boolean) aContext.getConfigParameterValue(PARAM_ANNOTATE_PARTOFSPEECH);
        model_path = (String) aContext.getConfigParameterValue(PARAM_MODEL_PATH);
        config_path = (String) aContext.getConfigParameterValue(PARAM_CONFIG_PATH);

        // check if the model file exists
        if(model_path == null || (new File(model_path)).exists() == false) {
            String message = "The supplied model file for the Stanford Tagger could not be found.";
            Logger.printError(getClass(), message);
            throw new IllegalArgumentException(message);
        }

        initTokenizerFactory();

        initMaxEntPosTagger();
    }


    /**
     * Method that gets called to process the documents' cas objects
     */
    public void process(JCas jcas) throws AnalysisEngineProcessException {

        String docText = jcas.getDocumentText();

        // get [sentence-tokens[word-tokens]] from the MaxentTagger
        List<List<HasWord>> tokenArray = MaxentTagger.tokenizeText(new StringReader(docText), tokenizerFactory);

        // iterate over sentences in this document
        for(List<HasWord> sentenceToken : tokenArray) {
            // only determine pos tags if required: save runtime
            ListIterator<TaggedWord> twit = null;
            if(annotate_tokens && annotate_partofspeech) {
                List<TaggedWord> taggedSentence = mt.tagSentence(sentenceToken);
                twit = taggedSentence.listIterator();
            }

            // init variables used int the following loop,
            // which are required to determine sentence boundaries afterwards
            boolean isFirstWord = true;
            int firstWordOfSentenceBegin = -1;
            int lastWordOfSentenceEnd = -1;

            // iterate over words in this sentence
            for(HasWord wordToken : sentenceToken) {
                // determine the wordTokens begin and end index within the document
                // see 'getOffsets' for further details
                HasOffset offsetToken = getOffsets(wordToken);


                if(isFirstWord) {
                    firstWordOfSentenceBegin = offsetToken.beginPosition();
                    isFirstWord = false;
                }


                if(annotate_tokens) {
                    Token jcasToken = new Token(jcas);

                    jcasToken.setBegin(offsetToken.beginPosition());
                    jcasToken.setEnd(offsetToken.endPosition());

                    // if pos is supposed to be added, iterate through the tagged tokens and set pos
                    if(annotate_partofspeech) {
                        TaggedWord taggedWord = twit.next();
                        jcasToken.setPos(taggedWord.tag());
                    }

                    jcasToken.addToIndexes();
                }

                lastWordOfSentenceEnd = offsetToken.endPosition();
            }

            if(firstWordOfSentenceBegin == -1 || lastWordOfSentenceEnd == -1) {
                //means sentence did not contain any word -> which does not make sense
                throw new UnexpectedStateException("Empty sentence");
            }

            // if flag is set, also tag sentences
            if(annotate_sentences) {
              Sentence sentence = new Sentence(jcas);
              sentence.setBegin(firstWordOfSentenceBegin);
              sentence.setEnd(lastWordOfSentenceEnd);
              sentence.addToIndexes();
            }
        }

    }

    /** Determines the document text offsets of a token
     *
     * MaxentTagger.tokenizeText returns tokens typed to interface 'HasWord', which does not has methods
     * to determine the begin and end offsets of the token/word.
     * Assume that begin and end offsets are always known after tokenization.
     * Further assume that the actual type implementing 'HasWord' also implements 'HasOffset', i.e.
     * the "unpleasant" type 'HasWord' is the consequence of the interface segregation principle
     * -> Simply cast
     *
     * @param wordToken
     * @return wordToken cast to the HasOffset interface
     */
    private HasOffset getOffsets(HasWord wordToken) {
      try {
          return (HasOffset) wordToken;
      } catch (ClassCastException ex) {
          // Wrap the class cast exception in order to simplify debugging
          throw new UnexpectedStateException("Assumption, that tokenizer returns token offsets violated", ex);
      }
    }

    private void initTokenizerFactory() {
        tokenizerFactory = PTBTokenizerFactory.newTokenizerFactory();
        tokenizerFactory.setOptions("ptb3Escaping=false,untokenizable=noneKeep");
    }


    private void initMaxEntPosTagger() {
        // try instantiating the MaxEnt Tagger
        try {
            if(config_path != null) { // configuration exists
                FileInputStream isr = new FileInputStream(config_path);
                Properties props = new Properties();
                props.load(isr);
                mt = new MaxentTagger(model_path, new TaggerConfig(props), false);
            } else { // instantiate without configuration file
                mt = new MaxentTagger(model_path, new TaggerConfig("-model", model_path), false);
            }
        } catch(Exception e) {
            String message = "MaxentTagger could not be instantiated with the supplied model(" + model_path + ") and config(" + config_path + ") file.";
            Logger.printError(getClass(), message);
            throw new IllegalArgumentException(message, e);
        }
    }



    public class UnexpectedStateException extends RuntimeException{
        public UnexpectedStateException() {
            super();
        }
        public UnexpectedStateException(String message) {
            super(message);
        }
        public UnexpectedStateException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}

