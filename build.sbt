name := "scala_spark"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "org.apache.kafka" %% "kafka" % "2.1.0")