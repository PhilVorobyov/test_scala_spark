name := "scala_spark"

version := "0.1"

scalaVersion := "2.11.12"

mainClass := Some("by.vorobyov.TestSpark")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.0",
  "org.apache.spark" %% "spark-sql" % "2.3.0",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test")