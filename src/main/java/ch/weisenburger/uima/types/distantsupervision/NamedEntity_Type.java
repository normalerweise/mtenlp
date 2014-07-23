
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
public class NamedEntity_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NamedEntity_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NamedEntity_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NamedEntity(addr, NamedEntity_Type.this);
  			   NamedEntity_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NamedEntity(addr, NamedEntity_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = NamedEntity.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.NamedEntity");
 
  /** @generated */
  final Feature casFeat_entityType;
  /** @generated */
  final int     casFeatCode_entityType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getEntityType(int addr) {
        if (featOkTst && casFeat_entityType == null)
      jcas.throwFeatMissing("entityType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_entityType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setEntityType(int addr, String v) {
        if (featOkTst && casFeat_entityType == null)
      jcas.throwFeatMissing("entityType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_entityType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dbpediaOntologyType;
  /** @generated */
  final int     casFeatCode_dbpediaOntologyType;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDbpediaOntologyType(int addr) {
        if (featOkTst && casFeat_dbpediaOntologyType == null)
      jcas.throwFeatMissing("dbpediaOntologyType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbpediaOntologyType);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDbpediaOntologyType(int addr, String v) {
        if (featOkTst && casFeat_dbpediaOntologyType == null)
      jcas.throwFeatMissing("dbpediaOntologyType", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbpediaOntologyType, v);}
    
  
 
  /** @generated */
  final Feature casFeat_dbpediaURI;
  /** @generated */
  final int     casFeatCode_dbpediaURI;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDbpediaURI(int addr) {
        if (featOkTst && casFeat_dbpediaURI == null)
      jcas.throwFeatMissing("dbpediaURI", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbpediaURI);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDbpediaURI(int addr, String v) {
        if (featOkTst && casFeat_dbpediaURI == null)
      jcas.throwFeatMissing("dbpediaURI", "ch.weisenburger.uima.types.distantsupervision.NamedEntity");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbpediaURI, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public NamedEntity_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_entityType = jcas.getRequiredFeatureDE(casType, "entityType", "uima.cas.String", featOkTst);
    casFeatCode_entityType  = (null == casFeat_entityType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_entityType).getCode();

 
    casFeat_dbpediaOntologyType = jcas.getRequiredFeatureDE(casType, "dbpediaOntologyType", "uima.cas.String", featOkTst);
    casFeatCode_dbpediaOntologyType  = (null == casFeat_dbpediaOntologyType) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dbpediaOntologyType).getCode();

 
    casFeat_dbpediaURI = jcas.getRequiredFeatureDE(casType, "dbpediaURI", "uima.cas.String", featOkTst);
    casFeatCode_dbpediaURI  = (null == casFeat_dbpediaURI) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dbpediaURI).getCode();

  }
}



    