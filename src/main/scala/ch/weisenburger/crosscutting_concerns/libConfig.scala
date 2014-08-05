package ch.weisenburger.crosscutting_concerns

import com.typesafe.config.ConfigFactory

object LibConfig {
  lazy val config = ConfigFactory.load();
}
trait LibConfig {
 def config = LibConfig.config

  def tripleStoreURL = config.getString("mtner.triplestore.url")

}
