package by.vorobyov.stream

import by.vorobyov.spark.QueryClient
import by.vorobyov.spark.QueryClient.loadSchema
import org.apache.spark.sql.SparkSession

object KafkaTest {
  def main(args: Array[String]) {
    val sc = SparkSession.builder.master("local[*]").getOrCreate()
    sc.sparkContext.setLogLevel("ERROR")
    val df = sc.read.format("csv")
      .schema(loadSchema())
      .load("src/main/input_data/train.csv")
    for (arg <- args) QueryClient.executeQuery(arg.toInt)
  }
}
