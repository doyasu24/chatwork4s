package com.dys.chatwork4s.exception

import com.dys.chatwork4s.beans.Errors

/**
  * Created by dys on 2017/02/06.
  */
class ChatWorkException(errors: Errors = Errors.empty) extends RuntimeException(errors.errors.mkString(","))
