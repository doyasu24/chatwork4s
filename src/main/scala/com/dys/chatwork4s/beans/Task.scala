package com.dys.chatwork4s.beans

import com.dys.chatwork4s.beans.rooms.RoomOutline
import com.dys.chatwork4s.beans.users.Account
import com.dys.chatwork4s.enum.TaskStatus
import com.dys.chatwork4s.enum.TaskStatus.TaskStatus
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}


/**
  * [
  * {
  * "task_id": 3,
  * "room": {
  * "room_id": 5,
  * "name": "Group Chat Name",
  * "icon_path": "https://example.com/ico_group.png"
  * },
  * "assigned_by_account": {
  * "account_id": 456,
  * "name": "Anna",
  * "avatar_image_url": "https://example.com/def.png"
  * },
  * "message_id": "13",
  * "body": "buy milk",
  * "limit_time": 1384354799,
  * "status": "open"
  * }
  * ]
  */
case class Task(
                 taskId: Long,
                 room: RoomOutline,
                 assignedByAccount: Account,
                 messageId: String,
                 body: String,
                 limitTime: Long,
                 status: TaskStatus
               )

object Task {
  implicit val reads: Reads[Task] = (
    (JsPath \ "task_id").read[Long] and
      (JsPath \ "room").read[RoomOutline] and
      (JsPath \ "assigned_by_account").read[Account] and
      (JsPath \ "message_id").read[String] and
      (JsPath \ "body").read[String] and
      (JsPath \ "limit_time").read[Long] and
      (JsPath \ "status").read[String].map(s => TaskStatus.parse(s).get)
    ) (Task.apply _)
}
