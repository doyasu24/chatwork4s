package com.dys.chatwork4s

import com.dys.chatwork4s.http.HttpMethodImpl

/**
  * Created by dys on 2017/02/06.
  */
object ChatWorkFactory {

  val serverUrl: String = "https://api.chatwork.com/v2"

  def syncAPI(token: String): ChatWork = {
    val method = new HttpMethodImpl(serverUrl, token)
    new ChatWork(method)
  }

  def asyncAPI(token: String): ChatWorkAsync = {
    val method = new HttpMethodImpl(serverUrl, token)
    new ChatWorkAsync(method)
  }
}
