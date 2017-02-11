package com.dys.chatwork4s.beans.users

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class IncomingRequest(
                            requestId: Long,
                            accountId: Int,
                            message: String,
                            name: String,
                            chatWorkId: String,
                            organizationId: Long,
                            organizationName: String,
                            department: String,
                            avatarImageUrl: String
                          ) extends User

object IncomingRequest {
  implicit val reads: Reads[IncomingRequest] = (
    (JsPath \ "request_id").read[Long] and
      (JsPath \ "account_id").read[Int] and
      (JsPath \ "message").read[String] and
      (JsPath \ "name").read[String] and
      (JsPath \ "chatwork_id").read[String] and
      (JsPath \ "organization_id").read[Long] and
      (JsPath \ "organization_name").read[String] and
      (JsPath \ "department").read[String] and
      (JsPath \ "avatar_image_url").read[String]
    ) (IncomingRequest.apply _)
}