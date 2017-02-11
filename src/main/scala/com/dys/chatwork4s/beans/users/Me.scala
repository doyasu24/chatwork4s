package com.dys.chatwork4s.beans.users

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * 自分自身の情報
  */
case class Me(
               accountId: Int,
               roomId: Long,
               name: String,
               chatWorkId: String,
               organizationId: Long,
               organizationName: String,
               department: String,
               title: String,
               url: String,
               introduction: String,
               mail: String,
               telOrganization: String,
               telExtension: String,
               telMobile: String,
               skype: String,
               facebook: String,
               twitter: String,
               avatarImageUrl: String
             ) extends User

object Me {
  implicit val reads: Reads[Me] = (
    (JsPath \ "account_id").read[Int] and
      (JsPath \ "room_id").read[Long] and
      (JsPath \ "name").read[String] and
      (JsPath \ "chatwork_id").read[String] and
      (JsPath \ "organization_id").read[Long] and
      (JsPath \ "organization_name").read[String] and
      (JsPath \ "department").read[String] and
      (JsPath \ "title").read[String] and
      (JsPath \ "url").read[String] and
      (JsPath \ "introduction").read[String] and
      (JsPath \ "mail").read[String] and
      (JsPath \ "tel_organization").read[String] and
      (JsPath \ "tel_extension").read[String] and
      (JsPath \ "tel_mobile").read[String] and
      (JsPath \ "skype").read[String] and
      (JsPath \ "facebook").read[String] and
      (JsPath \ "twitter").read[String] and
      (JsPath \ "avatar_image_url").read[String]
    ) (Me.apply _)
}
