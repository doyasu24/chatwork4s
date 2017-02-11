package com.dys.chatwork4s

import com.dys.chatwork4s.beans.rooms.RoomInfoWithoutDescription
import com.dys.chatwork4s.beans.users.{Contact, IncomingRequest, Me}
import com.dys.chatwork4s.beans.{MyStatus, RoomId, Task}
import com.dys.chatwork4s.http.HttpMethod
import com.dys.chatwork4s.http.parameters.{CreateRoom, GetTask}
import com.dys.chatwork4s.utils.Sync._

import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.Duration


/**
  * Created by dys on 2017/02/06.
  */
class Chatwork(httpMethod: HttpMethod) {

  private val chatworkAsync = new ChatworkAsync(httpMethod)

  implicit private val atMost: Duration = Duration.Inf

  implicit private val ec = global

  def close(): Unit = chatworkAsync.close()

  /**
    * チャットルームごとのAPI
    *
    * @param roomId roomId
    * @return
    */
  def room(roomId: Long): ChatworkRoom = new ChatworkRoom(roomId, httpMethod)

  /**
    * 自分自身の情報を取得
    *
    * @return
    */
  def me(): Me = await[Me](chatworkAsync.me())

  /**
    * 自分の未読数、未読To数、未完了タスク数を返す
    *
    * @return
    */
  def myStatus(): MyStatus = await[MyStatus](chatworkAsync.myStatus())

  /**
    * 自分のタスク一覧を取得する。(※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getTask parameters
    * @return
    */
  def myTasks(getTask: GetTask = GetTask.empty): Seq[Task] = await[Seq[Task]](chatworkAsync.myTasks(getTask))

  /**
    * 自分のコンタクト一覧を取得
    *
    * @return
    */
  def contacts(): Seq[Contact] = await[Seq[Contact]](chatworkAsync.contacts())

  /**
    * 自分のチャット一覧の取得
    *
    * @return
    */
  def rooms(): Seq[RoomInfoWithoutDescription] = await[Seq[RoomInfoWithoutDescription]](chatworkAsync.rooms())

  /**
    * グループチャットを新規作成
    *
    * @param createRoom parameters
    * @return
    */
  def createRoom(createRoom: CreateRoom): RoomId = await[RoomId](chatworkAsync.createRoom(createRoom))

  /**
    * 自分に対するコンタクト承認依頼一覧を取得する(※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @return
    */
  def incomingRequests(): Seq[IncomingRequest] = await[Seq[IncomingRequest]](chatworkAsync.incomingRequests())

  /**
    * 自分に対するコンタクト承認依頼を承認する
    *
    * @param requestId requestId
    * @return
    */
  def approveRequest(requestId: Long): Contact = await[Contact](chatworkAsync.approveRequest(requestId))

  /**
    * 自分に対するコンタクト承認依頼をキャンセルする
    *
    * @param requestId requestId
    * @return
    */
  def cancelRequest(requestId: Long): Unit = await[Unit](chatworkAsync.cancelRequest(requestId))
}
