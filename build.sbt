name := "scala_spark"

version := "0.1"

scalaVersion := "2.11.12"

enablePlugins(JmhPlugin)

val sparkVersion = "2.2.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.apache.kafka" %% "kafka" % "2.1.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.3.0",
  "org.apache.kafka" %% "kafka-streams-scala" % "2.2.1",
  "org.scala-lang" % "scala-library" % scalaVersion.value,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.6.7.2",
  "net.liftweb" %% "lift-json" % "2.6-M4"
)