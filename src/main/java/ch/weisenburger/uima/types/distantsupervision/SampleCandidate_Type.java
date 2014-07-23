
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

/** Sample
 * Updated by JCasGen Mon Jul 14 15:41:20 CEST 2014
 * @generated */
public class SampleCandidate_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (SampleCandidate_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = SampleCandidate_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new SampleCandidate(addr, SampleCandidate_Type.this);
  			   SampleCandidate_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new SampleCandidate(addr, SampleCandidate_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = SampleCandidate.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
 
  /** @generated */
  final Feature casFeat_containsObject;
  /** @generated */
  final int     casFeatCode_containsObject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContainsObject(int addr) {
        if (featOkTst && casFeat_containsObject == null)
      jcas.throwFeatMissing("containsObject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsObject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsObject(int addr, boolean v) {
        if (featOkTst && casFeat_containsObject == null)
      jcas.throwFeatMissing("containsObject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsObject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_containsSubject;
  /** @generated */
  final int     casFeatCode_containsSubject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContainsSubject(int addr) {
        if (featOkTst && casFeat_containsSubject == null)
      jcas.throwFeatMissing("containsSubject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsSubject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsSubject(int addr, boolean v) {
        if (featOkTst && casFeat_containsSubject == null)
      jcas.throwFeatMissing("containsSubject", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsSubject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_containsPredicate;
  /** @generated */
  final int     casFeatCode_containsPredicate;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContainsPredicate(int addr) {
        if (featOkTst && casFeat_containsPredicate == null)
      jcas.throwFeatMissing("containsPredicate", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsPredicate);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsPredicate(int addr, boolean v) {
        if (featOkTst && casFeat_containsPredicate == null)
      jcas.throwFeatMissing("containsPredicate", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsPredicate, v);}
    
  
 
  /** @generated */
  final Feature casFeat_containsTimex;
  /** @generated */
  final int     casFeatCode_containsTimex;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public boolean getContainsTimex(int addr) {
        if (featOkTst && casFeat_containsTimex == null)
      jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsTimex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsTimex(int addr, boolean v) {
        if (featOkTst && casFeat_containsTimex == null)
      jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsTimex, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentcence;
  /** @generated */
  final int     casFeatCode_sentcence;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentcence(int addr) {
        if (featOkTst && casFeat_sentcence == null)
      jcas.throwFeatMissing("sentcence", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentcence);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentcence(int addr, int v) {
        if (featOkTst && casFeat_sentcence == null)
      jcas.throwFeatMissing("sentcence", "ch.weisenburger.uima.types.distantsupervision.SampleCandidate");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentcence, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public SampleCandidate_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_containsObject = jcas.getRequiredFeatureDE(casType, "containsObject", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsObject  = (null == casFeat_containsObject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsObject).getCode();

 
    casFeat_containsSubject = jcas.getRequiredFeatureDE(casType, "containsSubject", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsSubject  = (null == casFeat_containsSubject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsSubject).getCode();

 
    casFeat_containsPredicate = jcas.getRequiredFeatureDE(casType, "containsPredicate", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsPredicate  = (null == casFeat_containsPredicate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsPredicate).getCode();

 
    casFeat_containsTimex = jcas.getRequiredFeatureDE(casType, "containsTimex", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsTimex  = (null == casFeat_containsTimex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsTimex).getCode();

 
    casFeat_sentcence = jcas.getRequiredFeatureDE(casType, "sentcence", "de.unihd.dbs.uima.types.heideltime.Sentence", featOkTst);
    casFeatCode_sentcence  = (null == casFeat_sentcence) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentcence).getCode();

  }
}



    