package com.dys.chatwork4s.http.parameters

/**
  * チャットのファイル一覧を取得する際に指定するパラメータ
  *
  * @param accountId アップロードしたユーザーのアカウントID
  */
case class GetFiles(
                     accountId: Option[Int] = None
                   ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("account_id", accountId)
  )
}

object GetFiles {
  val empty: GetFiles = GetFiles()
}