

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
public class NamedEntity extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntity.class);
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
  protected NamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public NamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public NamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public NamedEntity(JCas jcas, int begin, int end) {
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
  //* Feature: entityType

  /** getter for entityType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getEntityType() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityType);}
    
  /** setter for entityType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setEntityType(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityType, v);}    
   
    
  //*--------------*
  //* Feature: dbpediaOntologyType

  /** getter for dbpediaOntologyType - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDbpediaOntologyType() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_dbpediaOntologyType == null)
      jcasType.jcas.throwFeatMissing("dbpediaOntologyType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_dbpediaOntologyType);}
    
  /** setter for dbpediaOntologyType - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDbpediaOntologyType(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_dbpediaOntologyType == null)
      jcasType.jcas.throwFeatMissing("dbpediaOntologyType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_dbpediaOntologyType, v);}    
   
    
  //*--------------*
  //* Feature: dbpediaURI

  /** getter for dbpediaURI - gets 
   * @generated
   * @return value of the feature 
   */
  public String getDbpediaURI() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_dbpediaURI == null)
      jcasType.jcas.throwFeatMissing("dbpediaURI", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_dbpediaURI);}
    
  /** setter for dbpediaURI - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setDbpediaURI(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_dbpediaURI == null)
      jcasType.jcas.throwFeatMissing("dbpediaURI", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_dbpediaURI, v);}    
  }

    