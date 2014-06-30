name := "hackpr-signups"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  "mysql" % "mysql-connector-java" % "5.1.29",
  "org.hibernate" % "hibernate-core" % "4.3.5.Final",
  "com.sendgrid" % "sendgrid-java" % "0.2.0",
  "com.google.code.gson"%"gson"%"2.2.4"
)     

play.Project.playScalaSettings
