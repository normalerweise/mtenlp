

/* First created by JCasGen Mon Jul 14 15:41:20 CEST 2014 */
package ch.weisenburger.uima.types.distantsupervision;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Jul 14 15:41:20 CEST 2014
 * XML source: /Users/norman/Uni/06_Master_Thesis/code/eclipse_workspace/UIMAStuff/SampleFinder.xml
 * @generated */
public class Value extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Value.class);
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
  protected Value() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Value(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Value(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Value(JCas jcas, int begin, int end) {
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
  //* Feature: valueType

  /** getter for valueType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getValueType() {
    if (Value_Type.featOkTst && ((Value_Type)jcasType).casFeat_valueType == null)
      jcasType.jcas.throwFeatMissing("valueType", "ch.weisenburger.uima.types.distantsupervision.Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Value_Type)jcasType).casFeatCode_valueType);}
    
  /** setter for valueType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setValueType(String v) {
    if (Value_Type.featOkTst && ((Value_Type)jcasType).casFeat_valueType == null)
      jcasType.jcas.throwFeatMissing("valueType", "ch.weisenburger.uima.types.distantsupervision.Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Value_Type)jcasType).casFeatCode_valueType, v);}    
   
    
  //*--------------*
  //* Feature: parsedNumericValue

  /** getter for parsedNumericValue - gets 
   * @generated
   * @return value of the feature 
   */
  public String getParsedNumericValue() {
    if (Value_Type.featOkTst && ((Value_Type)jcasType).casFeat_parsedNumericValue == null)
      jcasType.jcas.throwFeatMissing("parsedNumericValue", "ch.weisenburger.uima.types.distantsupervision.Value");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Value_Type)jcasType).casFeatCode_parsedNumericValue);}
    
  /** setter for parsedNumericValue - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParsedNumericValue(String v) {
    if (Value_Type.featOkTst && ((Value_Type)jcasType).casFeat_parsedNumericValue == null)
      jcasType.jcas.throwFeatMissing("parsedNumericValue", "ch.weisenburger.uima.types.distantsupervision.Value");
    jcasType.ll_cas.ll_setStringValue(addr, ((Value_Type)jcasType).casFeatCode_parsedNumericValue, v);}    
  }

    