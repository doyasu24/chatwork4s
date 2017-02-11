package com.dys.chatwork4s.beans

import com.dys.chatwork4s.beans.users.Account
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * [
  * {
  * "file_id": 3,
  * "account": {
  * "account_id": 123,
  * "name": "Bob",
  * "avatar_image_url": "https://example.com/ico_avatar.png"
  * },
  * "message_id": "22",
  * "filename": "README.md",
  * "filesize": 2232,
  * "upload_time": 1384414750
  * }
  * ]
  */
case class File(
                 fileId: Long,
                 account: Account,
                 messageId: String,
                 fileName: String,
                 fileSize: Long,
                 uploadTime: Long,
                 downloadUrl: Option[String]
               )

object File {
  implicit val reads: Reads[File] = (
    (JsPath \ "file_id").read[Long] and
      (JsPath \ "account").read[Account] and
      (JsPath \ "message_id").read[String] and
      (JsPath \ "filename").read[String] and
      (JsPath \ "filesize").read[Long] and
      (JsPath \ "upload_time").read[Long] and
      (JsPath \ "download_url").readNullable[String]
    ) (File.apply _)
}
