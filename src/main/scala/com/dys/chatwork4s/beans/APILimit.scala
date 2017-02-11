package com.dys.chatwork4s.beans

/**
  * ChatWorkAPIの利用回数制限情報
  * APIのリクエスト数は、5分あたり100回までとなります。（APIの利用回数は、今後変更される可能性があります）
  * この制限を超えた場合、APIのレスポンスが 429 Too Many Requests エラーを返すようになります。
  *
  * @param limit     最大コール回数
  * @param remaining 残りコール回数
  * @param reset     次に制限がリセットされる時間（Unix time）
  */
case class APILimit(
                     limit: Int,
                     remaining: Int,
                     reset: Long
                   ) {
  override def toString: String = s"APILimit(limit = $limit, remaining = $remaining, reset = $reset)"
}
