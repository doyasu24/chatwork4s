package com.dys.chatwork4s.enum

/**
  * Created by dys on 2017/02/05.
  */
object RoomType extends EnumType {

  case object My extends RoomType("my")

  case object Direct extends RoomType("direct")

  case object Group extends RoomType("group")

  val values = List(My, Direct, Group)

  def parse(name: String): Option[RoomType] = values.find(_.name == name)

  sealed abstract class RoomType(val name: String) extends EnumType

}
