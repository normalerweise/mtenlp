
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
 * Updated by JCasGen Wed Jul 16 16:26:03 CEST 2014
 * @generated */
public class Revision_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Revision_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Revision_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Revision(addr, Revision_Type.this);
  			   Revision_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Revision(addr, Revision_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Revision.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.Revision");
 
  /** @generated */
  final Feature casFeat_dbpediaResourceURI;
  /** @generated */
  final int     casFeatCode_dbpediaResourceURI;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getDbpediaResourceURI(int addr) {
        if (featOkTst && casFeat_dbpediaResourceURI == null)
      jcas.throwFeatMissing("dbpediaResourceURI", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return ll_cas.ll_getStringValue(addr, casFeatCode_dbpediaResourceURI);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setDbpediaResourceURI(int addr, String v) {
        if (featOkTst && casFeat_dbpediaResourceURI == null)
      jcas.throwFeatMissing("dbpediaResourceURI", "ch.weisenburger.uima.types.distantsupervision.Revision");
    ll_cas.ll_setStringValue(addr, casFeatCode_dbpediaResourceURI, v);}
    
  
 
  /** @generated */
  final Feature casFeat_wikipediaRevNumber;
  /** @generated */
  final int     casFeatCode_wikipediaRevNumber;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public long getWikipediaRevNumber(int addr) {
        if (featOkTst && casFeat_wikipediaRevNumber == null)
      jcas.throwFeatMissing("wikipediaRevNumber", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return ll_cas.ll_getLongValue(addr, casFeatCode_wikipediaRevNumber);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWikipediaRevNumber(int addr, long v) {
        if (featOkTst && casFeat_wikipediaRevNumber == null)
      jcas.throwFeatMissing("wikipediaRevNumber", "ch.weisenburger.uima.types.distantsupervision.Revision");
    ll_cas.ll_setLongValue(addr, casFeatCode_wikipediaRevNumber, v);}
    
  
 
  /** @generated */
  final Feature casFeat_wikipediaArticleName;
  /** @generated */
  final int     casFeatCode_wikipediaArticleName;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getWikipediaArticleName(int addr) {
        if (featOkTst && casFeat_wikipediaArticleName == null)
      jcas.throwFeatMissing("wikipediaArticleName", "ch.weisenburger.uima.types.distantsupervision.Revision");
    return ll_cas.ll_getStringValue(addr, casFeatCode_wikipediaArticleName);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setWikipediaArticleName(int addr, String v) {
        if (featOkTst && casFeat_wikipediaArticleName == null)
      jcas.throwFeatMissing("wikipediaArticleName", "ch.weisenburger.uima.types.distantsupervision.Revision");
    ll_cas.ll_setStringValue(addr, casFeatCode_wikipediaArticleName, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Revision_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_dbpediaResourceURI = jcas.getRequiredFeatureDE(casType, "dbpediaResourceURI", "uima.cas.String", featOkTst);
    casFeatCode_dbpediaResourceURI  = (null == casFeat_dbpediaResourceURI) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_dbpediaResourceURI).getCode();

 
    casFeat_wikipediaRevNumber = jcas.getRequiredFeatureDE(casType, "wikipediaRevNumber", "uima.cas.Long", featOkTst);
    casFeatCode_wikipediaRevNumber  = (null == casFeat_wikipediaRevNumber) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_wikipediaRevNumber).getCode();

 
    casFeat_wikipediaArticleName = jcas.getRequiredFeatureDE(casType, "wikipediaArticleName", "uima.cas.String", featOkTst);
    casFeatCode_wikipediaArticleName  = (null == casFeat_wikipediaArticleName) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_wikipediaArticleName).getCode();

  }
}



    