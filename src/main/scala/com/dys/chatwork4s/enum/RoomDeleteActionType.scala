package com.dys.chatwork4s.enum

/**
  * Created by dys on 2017/02/05.
  */
object RoomDeleteActionType extends EnumType {

  case object Leave extends RoomDeleteActionType("leave")

  case object Delete extends RoomDeleteActionType("delete")

  sealed abstract class RoomDeleteActionType(val name: String) extends EnumType

}
