package com.dys.chatwork4s.http.parameters

import com.dys.chatwork4s.enum.RoomDeleteActionType.RoomDeleteActionType

/**
  * グループチャットを退席/削除する際に指定するパラメータ
  *
  * @param actionType 退席するか、削除するか<br>退席すると、このグループチャットにある自分が担当のタスク、および自分が送信したファイルは削除されます。 削除すると、このグループチャットに参加しているメンバー全員のメッセージ、タスク、ファイルはすべて削除されます。 ※一度削除すると元に戻せません！
  */
case class DeleteRoom(
                       actionType: RoomDeleteActionType
                     ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("action_type", Some(actionType.name))
  )
}
