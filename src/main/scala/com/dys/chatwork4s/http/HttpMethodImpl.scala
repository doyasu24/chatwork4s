package com.dys.chatwork4s.http

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.dys.chatwork4s.beans.APILimit
import com.dys.chatwork4s.http.parameters.HttpParameter
import com.dys.chatwork4s.log.ChatWorkLazyLogging
import play.api.http.Status
import play.api.libs.json.Reads
import play.api.libs.ws.ahc.AhcWSClient
import play.api.libs.ws.{WSRequest, WSResponse}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

class HttpMethodImpl(
                      val serverUrl: String,
                      token: String,
                      timeLimit: Long = 20000
                    ) extends HttpMethod with ChatWorkLazyLogging {

  private implicit val system = ActorSystem()
  private implicit val materializer = ActorMaterializer()

  // see https://www.playframework.com/documentation/2.5.x/ScalaWS
  private val wsClient = AhcWSClient()

  private val tokenHeaderKey = "X-ChatWorkToken"

  def get[L, R](api: String, parameter: HttpParameter = HttpParameter.empty, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]] = {
    logger.debug(s"$serverUrl$api: ${parameter.toParameters.mkString(",")}")

    request(api, parameter.toParameters)
      .get()
      .map(r => parse(r, default)(readsL, readsR))
  }

  def put[L, R](api: String, parameter: HttpParameter, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]] = {
    logger.debug(s"$serverUrl$api: ${parameter.toParameters.mkString(",")}")

    request(api)
      .put(seq2Map(parameter.toParameters))
      .map(r => parse(r, default)(readsL, readsR))
  }

  def post[L, R](api: String, parameter: HttpParameter, default: Option[R] = None)(
    implicit readsL: Reads[L], readsR: Reads[R], ec: ExecutionContext
  ): Future[Either[L, R]] = {
    logger.debug(s"$serverUrl$api: ${parameter.toParameters.mkString(",")}")

    request(api)
      .post(seq2Map(parameter.toParameters))
      .map(r => parse(r, default)(readsL, readsR))
  }

  def delete[L](api: String, parameter: HttpParameter = HttpParameter.empty)(
    implicit readsL: Reads[L], ec: ExecutionContext
  ): Future[Either[L, Unit]] = {
    logger.debug(s"$serverUrl$api: ${parameter.toParameters.mkString(",")}")

    request(api, parameter.toParameters)
      .delete()
      .map(r => parseUnit(r))
  }

  def close(): Unit = {
    wsClient.close()
    system.terminate()
  }

  private def parseUnit[L](response: WSResponse)(implicit readsL: Reads[L]): Either[L, Unit] = {
    debug(response)
    if (response.status != Status.NO_CONTENT) {
      parseLeft[L, Unit](response)
    } else {
      Right(Unit)
    }
  }

  private def parse[L, R](response: WSResponse, default: Option[R])(
    implicit readsL: Reads[L], readsR: Reads[R]
  ): Either[L, R] = {
    debug(response)
    if (response.status != Status.NO_CONTENT) {
      response.json.asOpt[R] match {
        case Some(r) => Right(r)
        case None => parseLeft[L, R](response)
      }
    } else {
      Right(default.get)
    }
  }

  private def parseLeft[L, R](response: WSResponse)(implicit readsL: Reads[L]): Either[L, R] = {
    response.json.validate[L].asEither match {
      case Right(l) => Left(l)
      case Left(e) => throw new Exception("json parse error: " + response.json.toString() + e)
    }
  }

  private def parseAPILimit(response: WSResponse): Option[APILimit] = {
    for (
      limit <- response.header("X-RateLimit-Limit");
      remaining <- response.header("X-RateLimit-Remaining");
      reset <- response.header("X-RateLimit-Reset")
    ) yield APILimit(limit.toInt, remaining.toInt, reset.toLong)
  }

  private def debug(response: WSResponse): Unit = {
    logger.debug(s"status = ${response.status}")
    logger.debug(response.allHeaders.toString())
    parseAPILimit(response).foreach(l => logger.debug(l.toString))
    logger.debug(response.body)
  }

  private def request(api: String, parameters: Seq[(String, String)] = Seq.empty): WSRequest = {
    wsClient.url(serverUrl + api)
      .withRequestTimeout(timeLimit.millis)
      .withHeaders((tokenHeaderKey, token))
      .withQueryString(parameters: _*)
  }

  private def seq2Map(seq: Seq[(String, String)]): Map[String, Seq[String]] = seq.map(e => (e._1, Seq(e._2))).toMap

}