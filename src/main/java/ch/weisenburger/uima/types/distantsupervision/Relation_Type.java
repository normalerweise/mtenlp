
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
public class Relation_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Relation_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Relation_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Relation(addr, Relation_Type.this);
  			   Relation_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Relation(addr, Relation_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Relation.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ch.weisenburger.uima.types.distantsupervision.Relation");
 
  /** @generated */
  final Feature casFeat_ontologyURI;
  /** @generated */
  final int     casFeatCode_ontologyURI;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getOntologyURI(int addr) {
        if (featOkTst && casFeat_ontologyURI == null)
      jcas.throwFeatMissing("ontologyURI", "ch.weisenburger.uima.types.distantsupervision.Relation");
    return ll_cas.ll_getStringValue(addr, casFeatCode_ontologyURI);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOntologyURI(int addr, String v) {
        if (featOkTst && casFeat_ontologyURI == null)
      jcas.throwFeatMissing("ontologyURI", "ch.weisenburger.uima.types.distantsupervision.Relation");
    ll_cas.ll_setStringValue(addr, casFeatCode_ontologyURI, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public Relation_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_ontologyURI = jcas.getRequiredFeatureDE(casType, "ontologyURI", "uima.cas.String", featOkTst);
    casFeatCode_ontologyURI  = (null == casFeat_ontologyURI) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_ontologyURI).getCode();

  }
}



    