# chatwork4s
An asynchronous / synchronous Scala library for ChatWork API

## Usage

### build.sbt

```scala
name := "chatwork-app"

version := "1.0"

scalaVersion := "2.11.8"

lazy val root = project.in(file(".")).dependsOn(githubRepo)

lazy val githubRepo = uri("git://github.com/kado-yasuyuki/chatwork4s.git")

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.1.9"

```

### Main: asyncAPI

```scala
import com.dys.chatwork4s.ChatworkFactory
import com.typesafe.scalalogging.LazyLogging

import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App with LazyLogging {

  val token = "your ChatWork API token"
  val chatworkAsync = ChatworkFactory.asyncAPI(token)

  chatworkAsync.me().foreach(result => {
    result match {
      case Right(me) =>
        logger.debug(s"name: ${me.name}, accountId: ${me.accountId}")
      case Left(e) =>
        logger.error(s"error response: $e")
    }
    chatworkAsync.close()
  })
}
```

### Main: syncAPI
```scala
import com.dys.chatwork4s.ChatworkFactory
import com.typesafe.scalalogging.LazyLogging

object Main extends App with LazyLogging {

  val token = "your ChatWork API token"
  val chatwork = ChatworkFactory.syncAPI(token)

  val me = chatwork.me()
  logger.debug(s"name: ${me.name}, accountId: ${me.accountId}")
  
  chatwork.close()
}
```

### logback.xml

```xml
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoders are assigned the type
             ch.qos.logback.classic.encoder.PatternLayoutEncoder by default -->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %marker %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <!-- play-ws -->
    <logger name="io.netty" level="WARN">
        <appender-ref ref="STDOUT"/>
    </logger>
    <logger name="org.asynchttpclient.netty" level="WARN">
        <appender-ref ref="STDOUT"/>
    </logger>

    <!-- chatwork4s -->
    <logger name="com.dys.chatwork4s" level="WARN">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root level="trace">
        <appender-ref ref="STDOUT"/>
    </root>

</configuration>
```


## License

Licensed under the Apache License, Version 2.0.
