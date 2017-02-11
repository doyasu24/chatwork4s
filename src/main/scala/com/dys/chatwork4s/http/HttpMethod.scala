package com.dys.chatwork4s.http

import com.dys.chatwork4s.http.parameters.HttpParameter
import play.api.libs.json.Reads

import scala.concurrent.{ExecutionContext, Future}

trait HttpMethod {

  val serverUrl: String

  def get[L, R](api: String, parameter: HttpParameter = HttpParameter.empty, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]]

  def put[L, R](api: String, parameter: HttpParameter, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]]

  def post[L, R](api: String, parameter: HttpParameter, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]]

  def delete[L](api: String, parameter: HttpParameter = HttpParameter.empty)(
    implicit readsL: Reads[L], ec: ExecutionContext
  ): Future[Either[L, Unit]]

  def close(): Unit
}
