package ch.weisenburger.uima

import ch.weisenburger.uima.annotator._
import ch.weisenburger.uima.config.HeidelTimeStandaloneConfig
import ch.weisenburger.uima.consumer._
import de.unihd.dbs.heideltime.standalone.DocumentType
import de.unihd.dbs.heideltime.standalone.components.impl.JCasFactoryImpl
import de.unihd.dbs.uima.annotator.heideltime.HeidelTime
import de.unihd.dbs.uima.annotator.heideltime.resources.Language
import de.unihd.dbs.uima.annotator.stanfordtagger.StanfordPOSTaggerWrapper
import org.apache.uima.UIMAFramework
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.util.XMLInputSource
import org.slf4j.LoggerFactory

/**
 * Created by Norman on 11.07.14.
 */
object FinancialDataSamplePipelineFactory extends HeidelTimeStandaloneConfig {

  private val log = LoggerFactory.getLogger(getClass)

  private val jcasFactory = createJcasFactory
  private val uimaContext = createUimaContext

  private lazy val posTagger = new MyStanfordPOSTaggerWrapper
  private lazy val sampleCandidateScalaCaseConverter = new SampleCandidateScalaCaseConverter
  private lazy val sampleScalaCaseConverter = new SampleScalaCaseConverter

  private def commmonComponents = Seq(
    posTagger,
    new HeidelTime,
    new CompanyAnnotator,
    new FinancialDataRelationAnnotator,
    new FormattedNumberAnnotator,
    new SampleAnnotator
  )

  private lazy val consoleComponents = Seq(
    new SampleCandidateHumanReadableWriter,
    new SampleHumanReadableWriter
  )


  def createTestPipeline(components: Seq[JCasAnnotator_ImplBase]) = synchronized {
    log.debug("creating test pipeline")
    components.foreach(c => c.initialize(uimaContext))
    new TestPipeline(components, jcasFactory.createJCas)
  }

  def createConsolePipeline = synchronized {
    log.debug("creating console pipeline")
    initUimaComponents
    new FinancialDataSampleConsolePipeline(commmonComponents ++ consoleComponents, jcasFactory.createJCas) }

  def createScalaPipeline = synchronized {
    log.debug("creating scala case class pipeline")
    initUimaComponents
    new FinancialDataSampleScalaCasePipeline(
      commmonComponents, jcasFactory.createJCas, sampleScalaCaseConverter, sampleCandidateScalaCaseConverter) }

  private lazy val initUimaComponents: Unit =
    (Seq(sampleScalaCaseConverter, sampleCandidateScalaCaseConverter) ++ consoleComponents ++ commmonComponents)
      .foreach(c => c.initialize(uimaContext))

  private def createUimaContext = {
    log.debug("creating uima context")
    new UimaContext(Language.ENGLISH, DocumentType.NARRATIVES)
  }

  private def createJcasFactory = {
    log.debug("creating jcas factory")
    def parseTypeSystemDescriptionFromPath(path: String) = UIMAFramework.getXMLParser.parseTypeSystemDescription(
      new XMLInputSource(getClass.getClassLoader.getResource(path)))

    val descriptions = Array(
      parseTypeSystemDescriptionFromPath(standaloneConfig.get(standaloneConfig.TYPESYSTEMHOME)),
      parseTypeSystemDescriptionFromPath(standaloneConfig.get(standaloneConfig.TYPESYSTEMHOME_DKPRO)),
      parseTypeSystemDescriptionFromPath("ch/weisenburger/uima/descriptors/type/SampleFinder.xml"))

    new JCasFactoryImpl(descriptions)
  }

}

trait Statistics {

  def stats: Map[String,String]

}

trait PipelineStatistics {

  protected val components: Seq[JCasAnnotator_ImplBase]

  def stats: Seq[(String,Map[String,String])] = components.filter(_.isInstanceOf[Statistics]).map(c => (c.getClass.getName, c.asInstanceOf[Statistics].stats))

}
