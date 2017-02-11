package com.dys.chatwork4s.beans.rooms

import com.dys.chatwork4s.enum.RoomType
import com.dys.chatwork4s.enum.RoomType.RoomType
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * {
  * "room_id": 123,
  * "name": "Group Chat Name",
  * "type": "group",
  * "role": "admin",
  * "sticky": false,
  * "unread_num": 10,
  * "mention_num": 1,
  * "mytask_num": 0,
  * "message_num": 122,
  * "file_num": 10,
  * "task_num": 17,
  * "icon_path": "https://example.com/ico_group.png",
  * "last_update_time": 1298905200,
  * "description": "room description text"
  * }
  */
case class RoomInfo(
                     roomId: Long,
                     name: String,
                     roomType: RoomType,
                     role: String,
                     sticky: Boolean,
                     unreadNum: Int,
                     mentionNum: Int,
                     myTaskNum: Int,
                     messageNum: Int,
                     fileNum: Int,
                     taskNum: Int,
                     iconPath: String,
                     lastUpdateTime: Long,
                     description: String
                   ) extends Room

object RoomInfo {
  implicit val reads: Reads[RoomInfo] = (
    (JsPath \ "room_id").read[Long] and
      (JsPath \ "name").read[String] and
      (JsPath \ "type").read[String].map(s => RoomType.parse(s).get) and
      (JsPath \ "role").read[String] and
      (JsPath \ "sticky").read[Boolean] and
      (JsPath \ "unread_num").read[Int] and
      (JsPath \ "mention_num").read[Int] and
      (JsPath \ "mytask_num").read[Int] and
      (JsPath \ "message_num").read[Int] and
      (JsPath \ "file_num").read[Int] and
      (JsPath \ "task_num").read[Int] and
      (JsPath \ "icon_path").read[String] and
      (JsPath \ "last_update_time").read[Long] and
      (JsPath \ "description").read[String]
    ) (RoomInfo.apply _)
}
