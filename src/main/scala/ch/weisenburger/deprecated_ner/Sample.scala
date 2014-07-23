package ch.weisenburger.deprecated_ner

/**
 * Created by Norman on 11.05.14.
 */
case class Sample(relationUri: String, entityUri: String, entityStartPos: Int, entityEndPos: Int, value: String, valueStartPos: Int, valueEndPos: Int, timex: String, sentence: String, revision: String, pageId: String) {


  // assuming the same sentece is extracted from several revisions
  override def equals(o: Any) = o match {
    case that: Sample =>
      that.relationUri.equalsIgnoreCase(this.relationUri) &&
      that.entityUri.equalsIgnoreCase(this.entityUri) &&
      that.value.equals(this.value) &&
      that.timex.equals(this.timex) &&
      that.pageId.equals(this.pageId)
    case _ => false
  }

  override def hashCode = {
    relationUri.toLowerCase.hashCode + entityUri.toLowerCase.hashCode +
      value.hashCode + timex.hashCode + pageId.hashCode
  }

}
