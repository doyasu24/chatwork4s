package com.dys.chatwork4s.beans

import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class MessageId(
                      messageId: String
                    )

object MessageId {
  implicit val reads: Reads[MessageId] = (JsPath \ "message_id").read[String].map(s => MessageId(s))
}
