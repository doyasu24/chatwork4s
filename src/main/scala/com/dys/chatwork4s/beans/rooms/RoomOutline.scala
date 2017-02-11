package com.dys.chatwork4s.beans.rooms

import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class RoomOutline(
                        roomId: Long,
                        name: String,
                        iconPath: String
                      ) extends Room

object RoomOutline {
  implicit val reads: Reads[RoomOutline] = (
    (JsPath \ "room_id").read[Long] and
      (JsPath \ "name").read[String] and
      (JsPath \ "icon_path").read[String]
    ) (RoomOutline.apply _)
}
