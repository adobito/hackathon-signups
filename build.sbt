import play.PlayScala

name := "hackathon-signups"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  filters,
  anorm,
  "mysql" % "mysql-connector-java" % "5.1.29",
  "org.hibernate" % "hibernate-core" % "4.3.5.Final",
  "org.hibernate" % "hibernate-c3p0" % "4.3.5.Final",
  "org.hibernate" % "hibernate-ehcache" % "4.3.5.Final",
  "com.sendgrid" % "sendgrid-java" % "1.0.0",
  "com.google.code.gson"%"gson"%"2.2.4",
  "com.google.oauth-client" % "google-oauth-client" % "1.18.0-rc"
)
