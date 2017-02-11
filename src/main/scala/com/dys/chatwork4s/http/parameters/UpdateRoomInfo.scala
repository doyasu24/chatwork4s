package com.dys.chatwork4s.http.parameters

import com.dys.chatwork4s.enum.IconPreset.IconPreset

/**
  * チャットの名前、アイコンをアップデートする際に指定するパラメータ
  *
  * @param description チャット概要<br>グループチャットの概要説明テキスト
  * @param iconPreset  アイコン種類<br>グループチャットのアイコン種類
  * @param name        グループチャット名<br>グループチャットのチャット名
  */
case class UpdateRoomInfo(
                           description: Option[String] = None,
                           iconPreset: Option[IconPreset] = None,
                           name: Option[String] = None
                         ) extends HttpParameter {
  override def toParameters: Seq[(String, String)] = singleParameter(
    ("description", description),
    ("icon_preset", iconPreset.map(_.name)),
    ("name", name)
  )
}
