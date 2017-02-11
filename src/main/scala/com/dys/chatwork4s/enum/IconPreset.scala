package com.dys.chatwork4s.enum

/**
  * グループチャットのアイコン種類
  */
object IconPreset extends EnumType {

  case object Group extends IconPreset("group")

  case object Check extends IconPreset("check")

  case object Document extends IconPreset("document")

  case object Meeting extends IconPreset("meeting")

  case object Event extends IconPreset("event")

  case object Project extends IconPreset("project")

  case object Business extends IconPreset("business")

  case object Study extends IconPreset("study")

  case object Security extends IconPreset("security")

  case object Star extends IconPreset("star")

  case object Idea extends IconPreset("idea")

  case object Heart extends IconPreset("heart")

  case object MagCup extends IconPreset("magcup")

  case object Beer extends IconPreset("beer")

  case object Music extends IconPreset("music")

  case object Sports extends IconPreset("sports")

  case object Travel extends IconPreset("travel")

  sealed abstract class IconPreset(val name: String) extends EnumType

}
