
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
public class Sample_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Sample_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Sample_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Sample(addr, Sample_Type.this);
  			   Sample_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Sample(addr, Sample_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Sample.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.Sample");
 
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
      jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getBooleanValue(addr, casFeatCode_containsTimex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setContainsTimex(int addr, boolean v) {
        if (featOkTst && casFeat_containsTimex == null)
      jcas.throwFeatMissing("containsTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setBooleanValue(addr, casFeatCode_containsTimex, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quadObject;
  /** @generated */
  final int     casFeatCode_quadObject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuadObject(int addr) {
        if (featOkTst && casFeat_quadObject == null)
      jcas.throwFeatMissing("quadObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getStringValue(addr, casFeatCode_quadObject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuadObject(int addr, String v) {
        if (featOkTst && casFeat_quadObject == null)
      jcas.throwFeatMissing("quadObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setStringValue(addr, casFeatCode_quadObject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quadSubject;
  /** @generated */
  final int     casFeatCode_quadSubject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuadSubject(int addr) {
        if (featOkTst && casFeat_quadSubject == null)
      jcas.throwFeatMissing("quadSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getStringValue(addr, casFeatCode_quadSubject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuadSubject(int addr, String v) {
        if (featOkTst && casFeat_quadSubject == null)
      jcas.throwFeatMissing("quadSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setStringValue(addr, casFeatCode_quadSubject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quadPredicate;
  /** @generated */
  final int     casFeatCode_quadPredicate;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuadPredicate(int addr) {
        if (featOkTst && casFeat_quadPredicate == null)
      jcas.throwFeatMissing("quadPredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getStringValue(addr, casFeatCode_quadPredicate);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuadPredicate(int addr, String v) {
        if (featOkTst && casFeat_quadPredicate == null)
      jcas.throwFeatMissing("quadPredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setStringValue(addr, casFeatCode_quadPredicate, v);}
    
  
 
  /** @generated */
  final Feature casFeat_quadTimex;
  /** @generated */
  final int     casFeatCode_quadTimex;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getQuadTimex(int addr) {
        if (featOkTst && casFeat_quadTimex == null)
      jcas.throwFeatMissing("quadTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getStringValue(addr, casFeatCode_quadTimex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setQuadTimex(int addr, String v) {
        if (featOkTst && casFeat_quadTimex == null)
      jcas.throwFeatMissing("quadTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setStringValue(addr, casFeatCode_quadTimex, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentcene;
  /** @generated */
  final int     casFeatCode_sentcene;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentcene(int addr) {
        if (featOkTst && casFeat_sentcene == null)
      jcas.throwFeatMissing("sentcene", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentcene);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentcene(int addr, int v) {
        if (featOkTst && casFeat_sentcene == null)
      jcas.throwFeatMissing("sentcene", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentcene, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentenceSubject;
  /** @generated */
  final int     casFeatCode_sentenceSubject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentenceSubject(int addr) {
        if (featOkTst && casFeat_sentenceSubject == null)
      jcas.throwFeatMissing("sentenceSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceSubject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceSubject(int addr, int v) {
        if (featOkTst && casFeat_sentenceSubject == null)
      jcas.throwFeatMissing("sentenceSubject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceSubject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentenceObject;
  /** @generated */
  final int     casFeatCode_sentenceObject;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentenceObject(int addr) {
        if (featOkTst && casFeat_sentenceObject == null)
      jcas.throwFeatMissing("sentenceObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceObject);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceObject(int addr, int v) {
        if (featOkTst && casFeat_sentenceObject == null)
      jcas.throwFeatMissing("sentenceObject", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceObject, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentencePredicate;
  /** @generated */
  final int     casFeatCode_sentencePredicate;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentencePredicate(int addr) {
        if (featOkTst && casFeat_sentencePredicate == null)
      jcas.throwFeatMissing("sentencePredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentencePredicate);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentencePredicate(int addr, int v) {
        if (featOkTst && casFeat_sentencePredicate == null)
      jcas.throwFeatMissing("sentencePredicate", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentencePredicate, v);}
    
  
 
  /** @generated */
  final Feature casFeat_sentenceTimex;
  /** @generated */
  final int     casFeatCode_sentenceTimex;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getSentenceTimex(int addr) {
        if (featOkTst && casFeat_sentenceTimex == null)
      jcas.throwFeatMissing("sentenceTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    return ll_cas.ll_getRefValue(addr, casFeatCode_sentenceTimex);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setSentenceTimex(int addr, int v) {
        if (featOkTst && casFeat_sentenceTimex == null)
      jcas.throwFeatMissing("sentenceTimex", "ch.weisenburger.uima.types.distantsupervision.Sample");
    ll_cas.ll_setRefValue(addr, casFeatCode_sentenceTimex, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Sample_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_containsTimex = jcas.getRequiredFeatureDE(casType, "containsTimex", "uima.cas.Boolean", featOkTst);
    casFeatCode_containsTimex  = (null == casFeat_containsTimex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_containsTimex).getCode();

 
    casFeat_quadObject = jcas.getRequiredFeatureDE(casType, "quadObject", "uima.cas.String", featOkTst);
    casFeatCode_quadObject  = (null == casFeat_quadObject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quadObject).getCode();

 
    casFeat_quadSubject = jcas.getRequiredFeatureDE(casType, "quadSubject", "uima.cas.String", featOkTst);
    casFeatCode_quadSubject  = (null == casFeat_quadSubject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quadSubject).getCode();

 
    casFeat_quadPredicate = jcas.getRequiredFeatureDE(casType, "quadPredicate", "uima.cas.String", featOkTst);
    casFeatCode_quadPredicate  = (null == casFeat_quadPredicate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quadPredicate).getCode();

 
    casFeat_quadTimex = jcas.getRequiredFeatureDE(casType, "quadTimex", "uima.cas.String", featOkTst);
    casFeatCode_quadTimex  = (null == casFeat_quadTimex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_quadTimex).getCode();

 
    casFeat_sentcene = jcas.getRequiredFeatureDE(casType, "sentcene", "de.unihd.dbs.uima.types.heideltime.Sentence", featOkTst);
    casFeatCode_sentcene  = (null == casFeat_sentcene) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentcene).getCode();

 
    casFeat_sentenceSubject = jcas.getRequiredFeatureDE(casType, "sentenceSubject", "ch.weisenburger.uima.types.distantsupervision.NamedEntity", featOkTst);
    casFeatCode_sentenceSubject  = (null == casFeat_sentenceSubject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceSubject).getCode();

 
    casFeat_sentenceObject = jcas.getRequiredFeatureDE(casType, "sentenceObject", "ch.weisenburger.uima.types.distantsupervision.Value", featOkTst);
    casFeatCode_sentenceObject  = (null == casFeat_sentenceObject) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceObject).getCode();

 
    casFeat_sentencePredicate = jcas.getRequiredFeatureDE(casType, "sentencePredicate", "ch.weisenburger.uima.types.distantsupervision.Relation", featOkTst);
    casFeatCode_sentencePredicate  = (null == casFeat_sentencePredicate) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentencePredicate).getCode();

 
    casFeat_sentenceTimex = jcas.getRequiredFeatureDE(casType, "sentenceTimex", "de.unihd.dbs.uima.types.heideltime.Timex3", featOkTst);
    casFeatCode_sentenceTimex  = (null == casFeat_sentenceTimex) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_sentenceTimex).getCode();

  }
}



    