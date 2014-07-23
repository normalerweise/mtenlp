

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
public class Relation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Relation.class);
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
  protected Relation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Relation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Relation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Relation(JCas jcas, int begin, int end) {
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
  //* Feature: ontologyURI

  /** getter for ontologyURI - gets 
   * @generated
   * @return value of the feature 
   */
  public String getOntologyURI() {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_ontologyURI == null)
      jcasType.jcas.throwFeatMissing("ontologyURI", "ch.weisenburger.uima.types.distantsupervision.Relation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Relation_Type)jcasType).casFeatCode_ontologyURI);}
    
  /** setter for ontologyURI - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setOntologyURI(String v) {
    if (Relation_Type.featOkTst && ((Relation_Type)jcasType).casFeat_ontologyURI == null)
      jcasType.jcas.throwFeatMissing("ontologyURI", "ch.weisenburger.uima.types.distantsupervision.Relation");
    jcasType.ll_cas.ll_setStringValue(addr, ((Relation_Type)jcasType).casFeatCode_ontologyURI, v);}    
  }

    