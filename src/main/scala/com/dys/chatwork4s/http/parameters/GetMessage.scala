package com.dys.chatwork4s.http.parameters

/**
  * チャットのメッセージ一覧を取得する際に指定するパラメータ
  *
  * @param force 未取得にかかわらず最新の100件を取得するか<br>trueを指定すると未取得にかかわらず最新の100件を取得します
  */
case class GetMessage(
                       force: Boolean = false
                     ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("force", Some(booleanToInt(force)))
  )
}

object GetMessage {
  val unreadOnly: GetMessage = GetMessage()
}