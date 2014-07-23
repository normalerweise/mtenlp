

/* First created by JCasGen Mon Jul 14 15:41:20 CEST 2014 */
package ch.weisenburger.uima.types.distantsupervision;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.unihd.dbs.uima.types.heideltime.Timex3;
import de.unihd.dbs.uima.types.heideltime.Sentence;
import org.apache.uima.jcas.tcas.Annotation;


/** Sample
 * Updated by JCasGen Mon Jul 14 15:41:20 CEST 2014
 * XML source: /Users/norman/Uni/06_Master_Thesis/code/eclipse_workspace/UIMAStuff/SampleFinder.xml
 * @generated */
public class Sample extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sample.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Sample() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Sample(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Sample(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Sample(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: containsTimex

  /** getter for containsTimex - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsTimex() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_containsTimex == null)
      jcasType.jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((Sample_Type)jcasType).casFeatCode_containsTimex);}
    
  /** setter for containsTimex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsTimex(boolean v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_containsTimex == null)
      jcasType.jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((Sample_Type)jcasType).casFeatCode_containsTimex, v);}    
   
    
  //*--------------*
  //* Feature: quadObject

  /** getter for quadObject - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuadObject() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadObject == null)
      jcasType.jcas.throwFeatMissing("quadObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadObject);}
    
  /** setter for quadObject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuadObject(String v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadObject == null)
      jcasType.jcas.throwFeatMissing("quadObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadObject, v);}    
   
    
  //*--------------*
  //* Feature: quadSubject

  /** getter for quadSubject - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuadSubject() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadSubject == null)
      jcasType.jcas.throwFeatMissing("quadSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadSubject);}
    
  /** setter for quadSubject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuadSubject(String v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadSubject == null)
      jcasType.jcas.throwFeatMissing("quadSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadSubject, v);}    
   
    
  //*--------------*
  //* Feature: quadPredicate

  /** getter for quadPredicate - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuadPredicate() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadPredicate == null)
      jcasType.jcas.throwFeatMissing("quadPredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadPredicate);}
    
  /** setter for quadPredicate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuadPredicate(String v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadPredicate == null)
      jcasType.jcas.throwFeatMissing("quadPredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadPredicate, v);}    
   
    
  //*--------------*
  //* Feature: quadTimex

  /** getter for quadTimex - gets 
   * @generated
   * @return value of the feature 
   */
  public String getQuadTimex() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadTimex == null)
      jcasType.jcas.throwFeatMissing("quadTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadTimex);}
    
  /** setter for quadTimex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setQuadTimex(String v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_quadTimex == null)
      jcasType.jcas.throwFeatMissing("quadTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sample_Type)jcasType).casFeatCode_quadTimex, v);}    
   
    
  //*--------------*
  //* Feature: sentcene

  /** getter for sentcene - gets 
   * @generated
   * @return value of the feature 
   */
  public Sentence getSentcene() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentcene == null)
      jcasType.jcas.throwFeatMissing("sentcene", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentcene)));}
    
  /** setter for sentcene - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentcene(Sentence v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentcene == null)
      jcasType.jcas.throwFeatMissing("sentcene", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentcene, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceSubject

  /** getter for sentenceSubject - gets 
   * @generated
   * @return value of the feature 
   */
  public NamedEntity getSentenceSubject() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceSubject == null)
      jcasType.jcas.throwFeatMissing("sentenceSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return (NamedEntity)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceSubject)));}
    
  /** setter for sentenceSubject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceSubject(NamedEntity v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceSubject == null)
      jcasType.jcas.throwFeatMissing("sentenceSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceSubject, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceObject

  /** getter for sentenceObject - gets 
   * @generated
   * @return value of the feature 
   */
  public Value getSentenceObject() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceObject == null)
      jcasType.jcas.throwFeatMissing("sentenceObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return (Value)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceObject)));}
    
  /** setter for sentenceObject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceObject(Value v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceObject == null)
      jcasType.jcas.throwFeatMissing("sentenceObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceObject, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentencePredicate

  /** getter for sentencePredicate - gets 
   * @generated
   * @return value of the feature 
   */
  public Relation getSentencePredicate() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentencePredicate == null)
      jcasType.jcas.throwFeatMissing("sentencePredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return (Relation)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentencePredicate)));}
    
  /** setter for sentencePredicate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentencePredicate(Relation v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentencePredicate == null)
      jcasType.jcas.throwFeatMissing("sentencePredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentencePredicate, jcasType.ll_cas.ll_getFSRef(v));}    
   
    
  //*--------------*
  //* Feature: sentenceTimex

  /** getter for sentenceTimex - gets 
   * @generated
   * @return value of the feature 
   */
  public Timex3 getSentenceTimex() {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceTimex == null)
      jcasType.jcas.throwFeatMissing("sentenceTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return (Timex3)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceTimex)));}
    
  /** setter for sentenceTimex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentenceTimex(Timex3 v) {
    if (Sample_Type.featOkTst && ((Sample_Type)jcasType).casFeat_sentenceTimex == null)
      jcasType.jcas.throwFeatMissing("sentenceTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    jcasType.ll_cas.ll_setRefValue(addr, ((Sample_Type)jcasType).casFeatCode_sentenceTimex, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    