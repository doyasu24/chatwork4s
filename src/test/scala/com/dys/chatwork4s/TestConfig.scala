package com.dys.chatwork4s

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper}
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.scala.DefaultScalaModule

/**
  * Created by dys on 2017/02/06.
  */
case class TestConfig(chatworkAPIUrl: String, token: String, testRoomId: Long)

object TestConfig {
  def load(): TestConfig = {
    val mapper = new ObjectMapper(new YAMLFactory)
    mapper.registerModule(DefaultScalaModule)
    mapper.enable(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)

    val is = this.getClass.getResourceAsStream("/test-config.yml")
    mapper.readValue[TestConfig](is, classOf[TestConfig])
  }
}

