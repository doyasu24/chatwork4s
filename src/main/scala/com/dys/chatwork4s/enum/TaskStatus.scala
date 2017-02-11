package com.dys.chatwork4s.enum

/**
  * タスクのステータス
  */
object TaskStatus extends EnumType {

  case object Open extends TaskStatus("open")

  case object Done extends TaskStatus("done")

  val values = List(Open, Done)

  def parse(name: String): Option[TaskStatus] = values.find(_.name == name)

  sealed abstract class TaskStatus(val name: String) extends EnumType

}
