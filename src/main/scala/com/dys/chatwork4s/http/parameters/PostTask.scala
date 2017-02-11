package com.dys.chatwork4s.http.parameters

/**
  * チャットに新しいタスクを追加
  *
  * @param body  タスクの内容
  * @param toIds タスクの期限<br>Unix timeで入力してください
  * @param limit 担当者のアカウントID
  */
case class PostTask(
                     body: String,
                     toIds: Seq[Int],
                     limit: Option[Long] = None
                   ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("body", Some(body)),
    ("to_ids", Some(toIds.mkString(","))),
    ("limit", limit)
  )
}
