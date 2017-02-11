package com.dys.chatwork4s

import com.dys.chatwork4s.beans._
import com.dys.chatwork4s.beans.rooms.RoomInfo
import com.dys.chatwork4s.beans.users.Member
import com.dys.chatwork4s.http.HttpMethod
import com.dys.chatwork4s.http.parameters._
import com.dys.chatwork4s.utils.Sync._

import scala.concurrent.ExecutionContext.global
import scala.concurrent.duration.Duration

/**
  * チャットルームにひもづくメッセージ、タスク、ファイル、概要、メンバー情報などにアクセスできます。
  *
  * @param roomId     roomId
  * @param httpMethod httpMethod
  */
class ChatworkRoom(val roomId: Long, httpMethod: HttpMethod) {

  private val room = new ChatworkRoomAsync(roomId, httpMethod)

  implicit private val atMost: Duration = Duration.Inf

  implicit private val ec = global

  /**
    * チャットの名前、アイコン、種類(my/direct/group)を取得
    *
    * @return
    */
  def roomInfo(): RoomInfo = await[RoomInfo](room.roomInfo)

  /**
    * チャットの名前、アイコンをアップデート
    *
    * @param updateRoomInfo parameters
    * @return
    */
  def updateRoomInfo(updateRoomInfo: UpdateRoomInfo): RoomId = await[RoomId](room.updateRoomInfo(updateRoomInfo))

  /**
    * グループチャットを退席/削除する
    *
    * @param deleteRoom parameters
    * @return
    */
  def deleteRoom(deleteRoom: DeleteRoom): Unit = await[Unit](room.deleteRoom(deleteRoom))

  /**
    * チャットのメンバー一覧を取得
    *
    * @return
    */
  def members(): Seq[Member] = await[Seq[Member]](room.members())

  /**
    * チャットのメンバーを一括変更
    *
    * @param updateMembers parameters
    * @return
    */
  def updateMembers(updateMembers: UpdateMembers): RoomMembers = await[RoomMembers](room.updateMembers(updateMembers))

  /**
    * チャットのメッセージ一覧を取得。パラメータ未指定だと前回取得分からの差分のみを返します。(最大100件まで取得)
    *
    * @param getMessage parameters
    * @return
    */
  def messages(getMessage: GetMessage = GetMessage.unreadOnly): Seq[Message] = await[Seq[Message]](room.messages(getMessage))

  /**
    * チャットに新しいメッセージを追加
    *
    * @param postMessage parameters
    * @return
    */
  def postMessage(postMessage: PostMessage): MessageId = await[MessageId](room.postMessage(postMessage))

  /**
    * メッセージ情報を取得
    *
    * @param messageId messageId
    * @return
    */
  def message(messageId: String): Message = await[Message](room.message(messageId))

  /**
    * チャットのタスク一覧を取得 (※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getTask parameters
    * @return
    */
  def tasks(getTask: GetTask = GetTask.empty): Seq[Task] = await[Seq[Task]](room.tasks(getTask))

  /**
    * チャットに新しいタスクを追加
    *
    * @param postTask parameters
    * @return
    */
  def postTask(postTask: PostTask): TaskIds = await[TaskIds](room.postTask(postTask))

  /**
    * タスク情報を取得
    *
    * @param taskId taskId
    * @return
    */
  def task(taskId: Long): Task = await[Task](room.task(taskId))

  /**
    * チャットのファイル一覧を取得 (※100件まで取得可能。今後、より多くのデータを取得する為のページネーションの仕組みを提供予定)
    *
    * @param getFiles parameters
    * @return
    */
  def files(getFiles: GetFiles = GetFiles.empty): Seq[File] = await[Seq[File]](room.files(getFiles))

  /**
    * ファイル情報を取得
    *
    * @param fileId  fileId
    * @param getFile parameters
    * @return
    */
  def file(fileId: Long, getFile: GetFile = GetFile.empty): File = await[File](room.file(fileId, getFile))
}
