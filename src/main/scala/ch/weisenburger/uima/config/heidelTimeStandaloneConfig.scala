package ch.weisenburger.uima.config

import java.util.Properties

import de.unihd.dbs.heideltime.standalone.Config

/**
 * Created by Norman on 14.07.14.
 */
object HeidelTimeStandaloneConfig {

  lazy val initConfig= {
    val configStream = getClass.getClassLoader.getResourceAsStream("heidel_time_and_standford_core.props")
    val props = new Properties
    props.load(configStream)
    Config.setProps(props)
    configStream.close
  }

  val TYPESYSTEMHOME = Config.TYPESYSTEMHOME
  val TYPESYSTEMHOME_DKPRO = Config.TYPESYSTEMHOME_DKPRO
  val UIMAVAR_DATE = Config.UIMAVAR_DATE
  val UIMAVAR_DURATION = Config.UIMAVAR_DURATION
  val UIMAVAR_LANGUAGE = Config.UIMAVAR_LANGUAGE
  val UIMAVAR_SET = Config.UIMAVAR_SET
  val UIMAVAR_TIME = Config.UIMAVAR_TIME
  val UIMAVAR_TYPETOPROCESS = Config.UIMAVAR_TYPETOPROCESS



  def get(key: String) = Config.get(key)

}

trait HeidelTimeStandaloneConfig {

  def standaloneConfig = { HeidelTimeStandaloneConfig.initConfig; HeidelTimeStandaloneConfig }

}
