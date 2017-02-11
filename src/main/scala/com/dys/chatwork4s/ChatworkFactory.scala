package com.dys.chatwork4s

import com.dys.chatwork4s.http.HttpMethodImpl

/**
  * Created by dys on 2017/02/06.
  */
object ChatworkFactory {

  val serverUrl: String = "https://api.chatwork.com/v2"

  def syncAPI(token: String): Chatwork = {
    val method = new HttpMethodImpl(serverUrl, token)
    new Chatwork(method)
  }

  def asyncAPI(token: String): ChatworkAsync = {
    val method = new HttpMethodImpl(serverUrl, token)
    new ChatworkAsync(method)
  }
}
