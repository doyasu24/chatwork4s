package com.dys.chatwork4s.beans

import play.api.libs.json.{JsPath, Reads}

/**
  * Created by dys on 2017/02/05.
  */
case class TaskIds(
                    taskIds: Seq[Int]
                  )

object TaskIds {
  implicit val reads: Reads[TaskIds] = (JsPath \ "task_ids").read[Seq[Int]].map(TaskIds.apply)
}
