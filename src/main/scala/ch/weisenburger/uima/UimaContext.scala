package ch.weisenburger.uima

import ch.weisenburger.crosscutting_concerns.LibConfig
import ch.weisenburger.uima.config.HeidelTimeStandaloneConfig
import de.unihd.dbs.heideltime.standalone.{Config, DocumentType}
import de.unihd.dbs.uima.annotator.heideltime.resources.Language
import de.unihd.dbs.uima.annotator.stanfordtagger.StanfordPOSTaggerWrapper
import org.apache.uima.impl.RootUimaContext_impl
import org.apache.uima.resource.impl.{ConfigurationManager_impl, ResourceManager_impl}


/**
 * Created by Norman on 07.07.14.
 */
private class UimaContext(language: Language, typeToProcess: DocumentType) extends RootUimaContext_impl with LibConfig with HeidelTimeStandaloneConfig {

  // Initialize config
  val configManager = new ConfigurationManager_impl


  // Initialize context
  private val init = {
    this.initializeRoot(null, new ResourceManager_impl, configManager)
    configManager.setSession(this.getSession)
  }


  private val setHeidelTimeSettings: Unit = {
    val heidelTimeLibConfig = config.getConfig("mtner.heideltime")
    val heidelTimeStanadaloneConfig = standaloneConfig

    configManager.setConfigParameterValue(makeQualifiedName(
      heidelTimeStanadaloneConfig.get(heidelTimeStanadaloneConfig.UIMAVAR_DATE)), heidelTimeLibConfig.getBoolean(Config.CONSIDER_DATE))


    configManager.setConfigParameterValue(makeQualifiedName(
      heidelTimeStanadaloneConfig.get(heidelTimeStanadaloneConfig.UIMAVAR_DURATION)), heidelTimeLibConfig.getBoolean(Config.CONSIDER_DURATION))


    configManager.setConfigParameterValue(makeQualifiedName(
      heidelTimeStanadaloneConfig.get(heidelTimeStanadaloneConfig.UIMAVAR_LANGUAGE)), language.getName)


    configManager.setConfigParameterValue(makeQualifiedName(
      heidelTimeStanadaloneConfig.get(heidelTimeStanadaloneConfig.UIMAVAR_SET)), heidelTimeLibConfig.getBoolean(Config.CONSIDER_SET))


    configManager.setConfigParameterValue(makeQualifiedName(
      HeidelTimeStandaloneConfig.get(HeidelTimeStandaloneConfig.UIMAVAR_TIME)), heidelTimeLibConfig.getBoolean(Config.CONSIDER_TIME))


    configManager.setConfigParameterValue(makeQualifiedName(
      heidelTimeStanadaloneConfig.get(heidelTimeStanadaloneConfig.UIMAVAR_TYPETOPROCESS)), typeToProcess.toString)
  }


  private val setStanfordPosTaggerSettings: Unit = {
    val stanfordPosTaggerConfig = config.getConfig("mtner.stanfordpostagger")
    configManager.setConfigParameterValue(makeQualifiedName(
      StanfordPOSTaggerWrapper.PARAM_MODEL_PATH), stanfordPosTaggerConfig.getString(StanfordPOSTaggerWrapper.PARAM_MODEL_PATH))

    if( stanfordPosTaggerConfig.hasPath(StanfordPOSTaggerWrapper.PARAM_CONFIG_PATH))
    configManager.setConfigParameterValue(makeQualifiedName(
      StanfordPOSTaggerWrapper.PARAM_CONFIG_PATH), stanfordPosTaggerConfig.getString(StanfordPOSTaggerWrapper.PARAM_CONFIG_PATH))

    configManager.setConfigParameterValue(makeQualifiedName(
      StanfordPOSTaggerWrapper.PARAM_ANNOTATE_TOKENS), stanfordPosTaggerConfig.getBoolean(StanfordPOSTaggerWrapper.PARAM_ANNOTATE_TOKENS))

    configManager.setConfigParameterValue(makeQualifiedName(
      StanfordPOSTaggerWrapper.PARAM_ANNOTATE_SENTENCES), stanfordPosTaggerConfig.getBoolean(StanfordPOSTaggerWrapper.PARAM_ANNOTATE_SENTENCES))

    configManager.setConfigParameterValue(makeQualifiedName(
      StanfordPOSTaggerWrapper.PARAM_ANNOTATE_PARTOFSPEECH), stanfordPosTaggerConfig.getBoolean(StanfordPOSTaggerWrapper.PARAM_ANNOTATE_PARTOFSPEECH))
  }



}
