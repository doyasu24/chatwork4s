package com.dys.chatwork4s.beans.users

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * [
  * {
  * "account_id": 123,
  * "role": "member",
  * "name": "John Smith",
  * "chatwork_id": "tarochatworkid",
  * "organization_id": 101,
  * "organization_name": "Hello Company",
  * "department": "Marketing",
  * "avatar_image_url": "https://example.com/abc.png"
  * }
  * ]
  */
case class Member(
                   accountId: Int,
                   role: String,
                   name: String,
                   chatWorkId: String,
                   organizationId: Long,
                   organizationName: String,
                   department: String,
                   avatarImageUrl: String
                 ) extends User

object Member {
  implicit val reads: Reads[Member] = (
    (JsPath \ "account_id").read[Int] and
      (JsPath \ "role").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "chatwork_id").read[String] and
      (JsPath \ "organization_id").read[Long] and
      (JsPath \ "organization_name").read[String] and
      (JsPath \ "department").read[String] and
      (JsPath \ "avatar_image_url").read[String]
    ) (Member.apply _)
}
