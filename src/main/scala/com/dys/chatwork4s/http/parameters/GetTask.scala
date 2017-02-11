package com.dys.chatwork4s.http.parameters

import com.dys.chatwork4s.enum.TaskStatus.TaskStatus

/**
  * 自分のタスク一覧を取得する際に指定するパラメータ
  *
  * @param assignedByAccountId タスクの依頼者のアカウントID
  * @param status              タスクのステータス
  */
case class GetTask(
                    assignedByAccountId: Option[Int] = None,
                    status: Option[TaskStatus] = None
                  ) extends HttpParameter {
  def toParameters: Seq[(String, String)] = singleParameter(
    ("assigned_by_account_id", assignedByAccountId),
    ("status", status.map(_.name))
  )
}

object GetTask {
  val empty: GetTask = GetTask(None, None)
}