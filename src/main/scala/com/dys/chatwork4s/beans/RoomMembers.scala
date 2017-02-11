package com.dys.chatwork4s.beans

import play.api.libs.json.{Json, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class RoomMembers(
                        admin: Seq[Int],
                        member: Seq[Int],
                        readonly: Seq[Int]
                      )

object RoomMembers {
  implicit val reads: Reads[RoomMembers] = Json.reads[RoomMembers]
}
