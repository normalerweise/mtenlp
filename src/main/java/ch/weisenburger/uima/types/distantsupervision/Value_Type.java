
/* First created by JCasGen Mon Jul 14 15:41:20 CEST 2014 */
package ch.weisenburger.uima.types.distantsupervision;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Mon Jul 14 15:41:20 CEST 2014
 * @generated */
public class Value_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Value_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Value_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Value(addr, Value_Type.this);
  			   Value_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Value(addr, Value_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Value.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.Value");
 
  /** @generated */
  final Feature casFeat_valueType;
  /** @generated */
  final int     casFeatCode_valueType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getValueType(int addr) {
        if (featOkTst && casFeat_valueType == null)
      jcas.throwFeatMissing("valueType", "ch.weisenburger.uima.types.distantsupervision.Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_valueType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setValueType(int addr, String v) {
        if (featOkTst && casFeat_valueType == null)
      jcas.throwFeatMissing("valueType", "ch.weisenburger.uima.types.distantsupervision.Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_valueType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_parsedNumericValue;
  /** @generated */
  final int     casFeatCode_parsedNumericValue;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getParsedNumericValue(int addr) {
        if (featOkTst && casFeat_parsedNumericValue == null)
      jcas.throwFeatMissing("parsedNumericValue", "ch.weisenburger.uima.types.distantsupervision.Value");
    return ll_cas.ll_getStringValue(addr, casFeatCode_parsedNumericValue);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setParsedNumericValue(int addr, String v) {
        if (featOkTst && casFeat_parsedNumericValue == null)
      jcas.throwFeatMissing("parsedNumericValue", "ch.weisenburger.uima.types.distantsupervision.Value");
    ll_cas.ll_setStringValue(addr, casFeatCode_parsedNumericValue, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Value_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_valueType = jcas.getRequiredFeatureDE(casType, "valueType", "uima.cas.String", featOkTst);
    casFeatCode_valueType  = (null == casFeat_valueType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_valueType).getCode();

 
    casFeat_parsedNumericValue = jcas.getRequiredFeatureDE(casType, "parsedNumericValue", "uima.cas.String", featOkTst);
    casFeatCode_parsedNumericValue  = (null == casFeat_parsedNumericValue) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_parsedNumericValue).getCode();

  }
}



    