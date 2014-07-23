

/* First created by JCasGen Mon Jul 14 15:41:20 CEST 2014 */
package ch.weisenburger.uima.types.distantsupervision;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Wed Jul 16 16:26:03 CEST 2014
 * XML source: /Users/norman/Uni/06_Master_Thesis/code/eclipse_workspace/UIMAStuff/SampleFinder.xml
 * @generated */
public class Revision extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Revision.class);
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
  protected Revision() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Revision(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Revision(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Revision(JCas jcas, int begin, int end) {
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
  //* Feature: dbpediaResourceURI

  /** getter for dbpediaResourceURI - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDbpediaResourceURI() {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_dbpediaResourceURI == null)
      jcasType.jcas.throwFeatMissing("dbpediaResourceURI", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Revision_Type)jcasType).casFeatCode_dbpediaResourceURI);}
    
  /** setter for dbpediaResourceURI - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDbpediaResourceURI(String v) {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_dbpediaResourceURI == null)
      jcasType.jcas.throwFeatMissing("dbpediaResourceURI", "ch.weisenburger.uima.types.distantsupervision.Revision");
    jcasType.ll_cas.ll_setStringValue(addr, ((Revision_Type)jcasType).casFeatCode_dbpediaResourceURI, v);}    
   
    
  //*--------------*
  //* Feature: wikipediaRevNumber

  /** getter for wikipediaRevNumber - gets 
   * @generated
   * @return value of the feature 
   */
  public long getWikipediaRevNumber() {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_wikipediaRevNumber == null)
      jcasType.jcas.throwFeatMissing("wikipediaRevNumber", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return jcasType.ll_cas.ll_getLongValue(addr, ((Revision_Type)jcasType).casFeatCode_wikipediaRevNumber);}
    
  /** setter for wikipediaRevNumber - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setWikipediaRevNumber(long v) {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_wikipediaRevNumber == null)
      jcasType.jcas.throwFeatMissing("wikipediaRevNumber", "ch.weisenburger.uima.types.distantsupervision.Revision");
    jcasType.ll_cas.ll_setLongValue(addr, ((Revision_Type)jcasType).casFeatCode_wikipediaRevNumber, v);}    
   
    
  //*--------------*
  //* Feature: wikipediaArticleName

  /** getter for wikipediaArticleName - gets 
   * @generated
   * @return value of the feature 
   */
  public String getWikipediaArticleName() {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_wikipediaArticleName == null)
      jcasType.jcas.throwFeatMissing("wikipediaArticleName", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Revision_Type)jcasType).casFeatCode_wikipediaArticleName);}
    
  /** setter for wikipediaArticleName - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setWikipediaArticleName(String v) {
    if (Revision_Type.featOkTst && ((Revision_Type)jcasType).casFeat_wikipediaArticleName == null)
      jcasType.jcas.throwFeatMissing("wikipediaArticleName", "ch.weisenburger.uima.types.distantsupervision.Revision");
    jcasType.ll_cas.ll_setStringValue(addr, ((Revision_Type)jcasType).casFeatCode_wikipediaArticleName, v);}    
  }

    