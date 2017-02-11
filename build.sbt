name := "chatwork4s"

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq(
  "-feature",
  "-deprecation",
  "-unchecked",
  "-Xlint"
)

crossPaths := false
autoScalaLibrary := false

publishTo := Some(Resolver.file("file",file("repo")))

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-ws_2.11" % "2.5.12",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0"
)

// for test
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "org.mockito" % "mockito-core" % "2.7.1" % "test",
  "ch.qos.logback" % "logback-classic" % "1.1.9" % "test",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.8.6" % "test",
  "org.scala-lang.modules" % "scala-xml_2.11" % "1.0.6" % "test",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.8.6" % "test"
)

assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

assemblyOutputPath in assembly := file("./chatwork4s.jar")
