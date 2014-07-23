package ch.weisenburger.crosscutting_concerns

import org.slf4j.LoggerFactory

/**
 * Created by Norman on 10.07.14.
 */
trait Logger {
  val log = LoggerFactory.getLogger(getClass)
}
