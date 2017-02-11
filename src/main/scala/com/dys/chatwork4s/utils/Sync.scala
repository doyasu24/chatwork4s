package com.dys.chatwork4s.utils

import com.dys.chatwork4s.beans.Errors
import com.dys.chatwork4s.exception.ChatworkException

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by dys on 2017/02/06.
  */
private[chatwork4s] object Sync {

  def await[T](f: Future[Either[Errors, T]])(implicit atMost: Duration): T = {
    Await.result[Either[Errors, T]](f, atMost) match {
      case Right(r) => r
      case Left(e) => throw new ChatworkException(e)
    }
  }
}
