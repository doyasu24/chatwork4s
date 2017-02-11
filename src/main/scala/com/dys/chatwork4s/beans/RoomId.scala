package com.dys.chatwork4s.beans

import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/04.
  */
case class RoomId(
                   roomId: Long
                 )

object RoomId {
  implicit val reads: Reads[RoomId] = (JsPath \ "room_id").read[Long].map(RoomId.apply)
}
