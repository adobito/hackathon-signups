name := "hackpr-signups"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  "mysql" % "mysql-connector-java" % "5.1.29",
  "org.hibernate" % "hibernate-core" % "4.3.5.Final",
  "org.hibernate" % "hibernate-c3p0" % "4.3.5.Final",
  "org.hibernate" % "hibernate-ehcache" % "4.3.5.Final",
  "com.sendgrid" % "sendgrid-java" % "0.2.0",
  "com.google.code.gson"%"gson"%"2.2.4"
)     

play.Project.playScalaSettings
