package com.dys.chatwork4s

import com.dys.chatwork4s.beans._
import com.dys.chatwork4s.beans.rooms.RoomInfoWithoutDescription
import com.dys.chatwork4s.beans.users.{Contact, IncomingRequest, Me}
import com.dys.chatwork4s.http.HttpMethod
import com.dys.chatwork4s.http.parameters._

import scala.concurrent.{ExecutionContext, Future}

/**
  * ChatWorkの非同期API
  */
class ChatWorkAsync(httpMethod: HttpMethod) {

  def close(): Unit = httpMethod.close()

  /**
    * チャットルームごとのAPI
    *
    * @param roomId roomId
    * @return
    */
  def room(roomId: Long): ChatWorkRoomAsync = new ChatWorkRoomAsync(roomId, httpMethod)

  /**
    * 自分自身の情報を取得
    *
    * @param ec ExecutionContext
    * @return
    */
  def me()(implicit ec: ExecutionContext): Future[Either[Errors, Me]] =
    httpMethod.get[Errors, Me]("/me")

  /**
    * 自分の未読数、未読To数、未完了タスク数を返す
    *
    * @param ec ExecutionContext
    * @return
    */
  def myStatus()(implicit ec: ExecutionContext): Future[Either[Errors, MyStatus]] =
    httpMethod.get[Errors, MyStatus]("/my/status")

  /**
    * 自分のタスク一覧を取得する。(※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getTask parameters
    * @param ec      ExecutionContext
    * @return
    */
  def myTasks(getTask: GetTask = GetTask.empty)(implicit ec: ExecutionContext): Future[Either[Errors, Seq[Task]]] =
    httpMethod.get[Errors, Seq[Task]]("/my/tasks", getTask, default = Some(Seq.empty))

  /**
    * 自分のコンタクト一覧を取得
    *
    * @param ec ExecutionContext
    * @return
    */
  def contacts()(implicit ec: ExecutionContext): Future[Either[Errors, Seq[Contact]]] =
    httpMethod.get[Errors, Seq[Contact]]("/contacts", default = Some(Seq.empty))

  /**
    * 自分のチャット一覧の取得
    *
    * @param ec ExecutionContext
    * @return
    */
  def rooms()(implicit ec: ExecutionContext): Future[Either[Errors, Seq[RoomInfoWithoutDescription]]] =
    httpMethod.get[Errors, Seq[RoomInfoWithoutDescription]]("/rooms")

  /**
    * グループチャットを新規作成
    *
    * @param createRoom parameters
    * @param ec         ExecutionContext
    * @return
    */
  def createRoom(createRoom: CreateRoom)(implicit ec: ExecutionContext): Future[Either[Errors, RoomId]] =
    httpMethod.post[Errors, RoomId]("/rooms", createRoom)

  /**
    * 自分に対するコンタクト承認依頼一覧を取得する(※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param ec ExecutionContext
    * @return
    */
  def incomingRequests()(implicit ec: ExecutionContext): Future[Either[Errors, Seq[IncomingRequest]]] =
    httpMethod.get[Errors, Seq[IncomingRequest]]("/incoming_requests", default = Some(Seq.empty))

  /**
    * 自分に対するコンタクト承認依頼を承認する
    *
    * @param requestId requestId
    * @param ec        ExecutionContext
    * @return
    */
  def approveRequest(requestId: Long)(implicit ec: ExecutionContext): Future[Either[Errors, Contact]] =
    httpMethod.get[Errors, Contact](s"/incoming_requests/$requestId")

  /**
    * 自分に対するコンタクト承認依頼をキャンセルする
    *
    * @param requestId requestId
    * @param ec        ExecutionContext
    * @return
    */
  def cancelRequest(requestId: Long)(implicit ec: ExecutionContext): Future[Either[Errors, Unit]] =
    httpMethod.delete[Errors](s"/incoming_requests/$requestId")
}
