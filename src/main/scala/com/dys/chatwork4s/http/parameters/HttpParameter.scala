package com.dys.chatwork4s.http.parameters

/**
  * Created by dys on 2017/02/05.
  */
trait HttpParameter {
  def toParameters: Seq[(String, String)]

  protected def singleParameter(ps: (String, Option[Any])*): Seq[(String, String)] = {
    ps.withFilter(_._2.isDefined).map(e => (e._1, e._2.get.toString))
  }

  protected def booleanToInt(b: Boolean): Int = {
    if (b) {
      1
    } else {
      0
    }
  }
}

object HttpParameter {
  val empty: HttpParameter = new HttpParameter {
    override def toParameters = Seq.empty[(String, String)]
  }
}