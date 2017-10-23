name := """play-sample"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  javaJdbc,
  filters,
  "org.mongodb" % "mongo-java-driver" % "3.4.2",
  "org.mongodb.morphia" % "morphia" % "1.3.2"
)