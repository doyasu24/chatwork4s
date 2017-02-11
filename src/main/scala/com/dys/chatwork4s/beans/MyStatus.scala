package com.dys.chatwork4s.beans

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * {
  * "unread_room_num": 2,
  * "mention_room_num": 1,
  * "mytask_room_num": 3,
  * "unread_num": 12,
  * "mention_num": 1,
  * "mytask_num": 8
  * }
  */
case class MyStatus(
                     unreadRoomNum: Int,
                     mentionRoomNum: Int,
                     myTaskRoomNum: Int,
                     unreadNum: Int,
                     mentionNum: Int,
                     myTaskNum: Int
                   )

object MyStatus {
  implicit val reads: Reads[MyStatus] = (
    (JsPath \ "unread_room_num").read[Int] and
      (JsPath \ "mention_room_num").read[Int] and
      (JsPath \ "mytask_room_num").read[Int] and
      (JsPath \ "unread_num").read[Int] and
      (JsPath \ "mention_num").read[Int] and
      (JsPath \ "mytask_num").read[Int]
    ) (MyStatus.apply _)
}
