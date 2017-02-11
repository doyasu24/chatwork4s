package com.dys.chatwork4s

import com.dys.chatwork4s.beans._
import com.dys.chatwork4s.beans.rooms.RoomInfo
import com.dys.chatwork4s.beans.users.Member
import com.dys.chatwork4s.http.HttpMethod
import com.dys.chatwork4s.http.parameters._

import scala.concurrent.{ExecutionContext, Future}


/**
  * チャットルームにひもづくメッセージ、タスク、ファイル、概要、メンバー情報などにアクセスできます。
  * 非同期API
  *
  * @param roomId     roomId
  * @param httpMethod httpMethod
  */
class ChatWorkRoomAsync(val roomId: Long, httpMethod: HttpMethod) {

  /**
    * チャットの名前、アイコン、種類(my/direct/group)を取得
    *
    * @param ec ExecutionContext
    * @return
    */
  def roomInfo()(implicit ec: ExecutionContext): Future[Either[Errors, RoomInfo]] =
    httpMethod.get[Errors, RoomInfo](s"/rooms/$roomId")

  /**
    * チャットの名前、アイコンをアップデート
    *
    * @param updateRoomInfo parameters
    * @param ec             ExecutionContext
    * @return
    */
  def updateRoomInfo(updateRoomInfo: UpdateRoomInfo)(implicit ec: ExecutionContext): Future[Either[Errors, RoomId]] =
    httpMethod.put[Errors, RoomId](s"/rooms/$roomId", updateRoomInfo)

  /**
    * グループチャットを退席/削除する
    *
    * @param deleteRoom parameters
    * @param ec         ExecutionContext
    * @return
    */
  def deleteRoom(deleteRoom: DeleteRoom)(implicit ec: ExecutionContext): Future[Either[Errors, Unit]] =
    httpMethod.delete[Errors](s"/rooms/$roomId", deleteRoom)

  /**
    * チャットのメンバー一覧を取得
    *
    * @param ec ExecutionContext
    * @return
    */
  def members()(implicit ec: ExecutionContext): Future[Either[Errors, Seq[Member]]] =
    httpMethod.get[Errors, Seq[Member]](s"/rooms/$roomId/members", default = Some(Seq.empty))

  /**
    * チャットのメンバーを一括変更
    *
    * @param updateMembers parameters
    * @param ec            ExecutionContext
    * @return
    */
  def updateMembers(updateMembers: UpdateMembers)(implicit ec: ExecutionContext): Future[Either[Errors, RoomMembers]] =
    httpMethod.put[Errors, RoomMembers](s"/rooms/$roomId/members", updateMembers)

  /**
    * チャットのメッセージ一覧を取得。パラメータ未指定だと前回取得分からの差分のみを返します。(最大100件まで取得)
    *
    * @param getMessage parameters
    * @param ec         ExecutionContext
    * @return
    */
  def messages(getMessage: GetMessage = GetMessage.unreadOnly)(implicit ec: ExecutionContext): Future[Either[Errors, Seq[Message]]] =
    httpMethod.get[Errors, Seq[Message]](s"/rooms/$roomId/messages", getMessage, default = Some(Seq.empty))

  /**
    * チャットに新しいメッセージを追加
    *
    * @param postMessage parameters
    * @param ec          ExecutionContext
    * @return
    */
  def postMessage(postMessage: PostMessage)(implicit ec: ExecutionContext): Future[Either[Errors, MessageId]] =
    httpMethod.post[Errors, MessageId](s"/rooms/$roomId/messages", postMessage)

  /**
    * メッセージ情報を取得
    *
    * @param messageId messageId
    * @param ec        ExecutionContext
    * @return
    */
  def message(messageId: String)(implicit ec: ExecutionContext): Future[Either[Errors, Message]] =
    httpMethod.get[Errors, Message](s"/rooms/$roomId/messages/$messageId")

  /**
    * チャットのタスク一覧を取得 (※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getTask parameters
    * @param ec      ExecutionContext
    * @return
    */
  def tasks(getTask: GetTask = GetTask.empty)(implicit ec: ExecutionContext): Future[Either[Errors, Seq[Task]]] =
    httpMethod.get[Errors, Seq[Task]](s"/rooms/$roomId/tasks", getTask, default = Some(Seq.empty))

  /**
    * チャットに新しいタスクを追加
    *
    * @param postTask parameters
    * @param ec       ExecutionContext
    * @return
    */
  def postTask(postTask: PostTask)(implicit ec: ExecutionContext): Future[Either[Errors, TaskIds]] =
    httpMethod.post[Errors, TaskIds](s"/rooms/$roomId/tasks", postTask)

  /**
    * タスク情報を取得
    *
    * @param taskId taskId
    * @param ec     ExecutionContext
    * @return
    */
  def task(taskId: Long)(implicit ec: ExecutionContext): Future[Either[Errors, Task]] =
    httpMethod.get[Errors, Task](s"/rooms/$roomId/tasks/$taskId")

  /**
    * チャットのファイル一覧を取得 (※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getFiles parameters
    * @param ec       ExecutionContext
    * @return
    */
  def files(getFiles: GetFiles = GetFiles.empty)(implicit ec: ExecutionContext): Future[Either[Errors, Seq[File]]] =
    httpMethod.get[Errors, Seq[File]](s"/rooms/$roomId/files", getFiles, default = Some(Seq.empty))

  /**
    * ファイル情報を取得
    *
    * @param fileId   fileId
    * @param fileInfo parameters
    * @param ec       ExecutionContext
    * @return
    */
  def file(fileId: Long, fileInfo: GetFile)(implicit ec: ExecutionContext): Future[Either[Errors, File]] =
    httpMethod.get[Errors, File](s"/rooms/$roomId/files/$fileId", fileInfo)
}

