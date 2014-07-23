

/* First created by JCasGen Mon Jul 14 15:41:20 CEST 2014 */
package ch.weisenburger.uima.types.distantsupervision;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import de.unihd.dbs.uima.types.heideltime.Sentence;
import org.apache.uima.jcas.tcas.Annotation;


/** Sample
 * Updated by JCasGen Mon Jul 14 15:41:20 CEST 2014
 * XML source: /Users/norman/Uni/06_Master_Thesis/code/eclipse_workspace/UIMAStuff/SampleFinder.xml
 * @generated */
public class SampleCandidate extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(SampleCandidate.class);
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
  protected SampleCandidate() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public SampleCandidate(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public SampleCandidate(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public SampleCandidate(JCas jcas, int begin, int end) {
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
  //* Feature: containsObject

  /** getter for containsObject - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsObject() {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsObject == null)
      jcasType.jcas.throwFeatMissing("containsObject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsObject);}
    
  /** setter for containsObject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsObject(boolean v) {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsObject == null)
      jcasType.jcas.throwFeatMissing("containsObject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsObject, v);}    
   
    
  //*--------------*
  //* Feature: containsSubject

  /** getter for containsSubject - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsSubject() {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsSubject == null)
      jcasType.jcas.throwFeatMissing("containsSubject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsSubject);}
    
  /** setter for containsSubject - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsSubject(boolean v) {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsSubject == null)
      jcasType.jcas.throwFeatMissing("containsSubject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsSubject, v);}    
   
    
  //*--------------*
  //* Feature: containsPredicate

  /** getter for containsPredicate - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsPredicate() {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsPredicate == null)
      jcasType.jcas.throwFeatMissing("containsPredicate", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsPredicate);}
    
  /** setter for containsPredicate - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsPredicate(boolean v) {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsPredicate == null)
      jcasType.jcas.throwFeatMissing("containsPredicate", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsPredicate, v);}    
   
    
  //*--------------*
  //* Feature: containsTimex

  /** getter for containsTimex - gets 
   * @generated
   * @return value of the feature 
   */
  public boolean getContainsTimex() {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsTimex == null)
      jcasType.jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return jcasType.ll_cas.ll_getBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsTimex);}
    
  /** setter for containsTimex - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setContainsTimex(boolean v) {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_containsTimex == null)
      jcasType.jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    jcasType.ll_cas.ll_setBooleanValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_containsTimex, v);}    
   
    
  //*--------------*
  //* Feature: sentcence

  /** getter for sentcence - gets 
   * @generated
   * @return value of the feature 
   */
  public Sentence getSentcence() {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_sentcence == null)
      jcasType.jcas.throwFeatMissing("sentcence", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return (Sentence)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_sentcence)));}
    
  /** setter for sentcence - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setSentcence(Sentence v) {
    if (SampleCandidate_Type.featOkTst && ((SampleCandidate_Type)jcasType).casFeat_sentcence == null)
      jcasType.jcas.throwFeatMissing("sentcence", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    jcasType.ll_cas.ll_setRefValue(addr, ((SampleCandidate_Type)jcasType).casFeatCode_sentcence, jcasType.ll_cas.ll_getFSRef(v));}    
  }

    