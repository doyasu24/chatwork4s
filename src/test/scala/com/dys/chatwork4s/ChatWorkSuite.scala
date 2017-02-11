package com.dys.chatwork4s

import com.dys.chatwork4s.http.parameters.PostMessage
import com.dys.chatwork4s.http.{HttpMethod, HttpMethodImpl}

/**
  * Created by dys on 2017/02/11.
  */
class ChatWorkSuite extends BaseSuite {

  val config: TestConfig = TestConfig.load()
  val httpMethod: HttpMethod = new HttpMethodImpl(config.chatWorkAPIUrl, config.token)
  val chatWork: ChatWork = new ChatWork(httpMethod)
  val room: ChatWorkRoom = chatWork.room(config.testRoomId)

  "message" should "be accessed by messageId" in {
    val testMessageBody = "The chatwork4s posted this message."
    val accountId = chatWork.me().accountId
    val messageId = room.postMessage(PostMessage(testMessageBody)).messageId
    val message = room.message(messageId)

    message.account.accountId shouldBe accountId
    message.body shouldBe testMessageBody
  }

}
