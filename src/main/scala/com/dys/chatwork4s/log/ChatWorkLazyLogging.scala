package com.dys.chatwork4s.log

import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Created by dys on 2017/02/11.
  */
trait ChatWorkLazyLogging {
  protected lazy val logger: Logger = Logger(LoggerFactory.getLogger(getClass.getName))
}
