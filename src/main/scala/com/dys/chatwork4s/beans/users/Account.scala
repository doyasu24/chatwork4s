package com.dys.chatwork4s.beans.users

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class Account(
                    accountId: Int,
                    name: String,
                    avatarImageUrl: String
                  ) extends User

object Account {
  implicit val reads: Reads[Account] = (
    (JsPath \ "account_id").read[Int] and
      (JsPath \ "name").read[String] and
      (JsPath \ "avatar_image_url").read[String]
    ) (Account.apply _)
}
