package com.dys.chatwork4s.http.parameters

/**
  * ファイル情報を取得する際に指定するパラメータ
  *
  * @param createDownloadUrl ダウンロードする為のURLを生成するか<br>30秒間だけダウンロード可能なURLを生成します
  */
case class GetFile(
                    createDownloadUrl: Option[Boolean] = None
                  ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("create_download_url", createDownloadUrl.map(booleanToInt))
  )
}

object GetFile {
  val empty: GetFile = GetFile()
}
