package com.dys.chatwork4s.beans

import com.dys.chatwork4s.beans.users.Account
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * [
  * {
  * "message_id": "5",
  * "account": {
  * "account_id": 123,
  * "name": "Bob",
  * "avatar_image_url": "https://example.com/ico_avatar.png"
  * },
  * "body": "Hello Chatwork!",
  * "send_time": 1384242850,
  * "update_time": 0
  * }
  * ]
  */
case class Message(
                    messageId: String,
                    account: Account,
                    body: String,
                    sendTime: Long,
                    updateTime: Long
                  )

object Message {
  implicit val reads: Reads[Message] = (
    (JsPath \ "message_id").read[String] and
      (JsPath \ "account").read[Account] and
      (JsPath \ "body").read[String] and
      (JsPath \ "send_time").read[Long] and
      (JsPath \ "update_time").read[Long]
    ) (Message.apply _)
}