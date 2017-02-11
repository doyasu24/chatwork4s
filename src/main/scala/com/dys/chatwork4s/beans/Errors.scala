package com.dys.chatwork4s.beans

import play.api.libs.json._

case class Errors(errors: Seq[String])

object Errors {
  // [errors] is an array with values ​​of various primitive types.
  implicit val reads: Reads[Errors] = (JsPath \ "errors").read[JsArray].map(a =>
    a.value.map {
      case JsNull => "null"
      case b: JsBoolean => b.value.toString
      case n: JsNumber => n.value.toString
      case s: JsString => s.value
      case _ => "" // not primitive
    }.toList
  ).map(Errors.apply)

  val empty: Errors = Errors(Seq.empty)
}