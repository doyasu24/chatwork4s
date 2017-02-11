package com.dys.chatwork4s.http.parameters

/**
  * チャットに新しいメッセージを追加する際に指定するパラメータ
  *
  * @param body メッセージ本文
  */
case class PostMessage(
                        body: String
                      ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("body", Some(body))
  )
}
