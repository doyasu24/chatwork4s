package com.dys.chatwork4s

import com.dys.chatwork4s.http.parameters.PostMessage
import com.dys.chatwork4s.http.{HttpMethod, HttpMethodImpl}

/**
  * Created by dys on 2017/02/11.
  */
class ChatworkSuite extends BaseSuite {

  val config: TestConfig = TestConfig.load()
  val httpMethod: HttpMethod = new HttpMethodImpl(config.chatworkAPIUrl, config.token)
  val chatwork: Chatwork = new Chatwork(httpMethod)
  val room: ChatworkRoom = chatwork.room(config.testRoomId)

  "message" should "be accessed by messageId" in {
    val testMessageBody = "The chatwork4s posted this message."
    val accountId = chatwork.me().accountId
    val messageId = room.postMessage(PostMessage(testMessageBody)).messageId
    val message = room.message(messageId)

    message.account.accountId shouldBe accountId
    message.body shouldBe testMessageBody
  }

}
