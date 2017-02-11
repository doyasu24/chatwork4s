package com.dys.chatwork4s.http.parameters

import com.dys.chatwork4s.enum.IconPreset.IconPreset

/**
  * グループチャットを新規作成する際に指定するパラメータ
  *
  * @param name        グループチャット名<br>作成したいグループチャットのチャット名
  * @param adminIds    管理者権限のユーザー<br>作成したチャットに参加メンバーのうち、管理者権限にしたいユーザーのアカウントIDの配列。最低1人は指定する必要がある。
  * @param description チャット概要<br>グループチャットの概要説明テキスト
  * @param iconPreset  アイコン種類<br>グループチャットのアイコン種類
  * @param memberIds   メンバー権限のユーザー<br>作成したチャットに参加メンバーのうち、メンバー権限にしたいユーザーのアカウントIDの配列。
  * @param readonlyIds 閲覧のみ権限のユーザー<br>作成したチャットに参加メンバーのうち、閲覧のみ権限にしたいユーザーのアカウントIDの配列。
  */
case class CreateRoom(
                       name: String,
                       adminIds: Seq[Int],
                       description: Option[String] = None,
                       iconPreset: Option[IconPreset] = None,
                       memberIds: Option[Seq[Int]] = None,
                       readonlyIds: Option[Seq[Int]] = None
                     ) extends HttpParameter {
  def toParameters: Seq[(String, String)] = singleParameter(
    ("name", Some(name)),
    ("members_admin_ids", Some(adminIds.mkString(","))),
    ("description", description),
    ("icon_preset", iconPreset.map(_.name)),
    ("members_member_ids", memberIds.map(_.mkString(","))),
    ("members_readonly_ids", readonlyIds.map(_.mkString(",")))
  )
}
