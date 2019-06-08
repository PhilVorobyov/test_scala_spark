package by.vorobyov.spark

import by.vorobyov.spark.QueryClient.loadSchema
import org.apache.spark.sql.SparkSession

object TestSpark {
  def main(args: Array[String]) {
    for (arg <- args) QueryClient.executeQuery(arg.toInt)
  }
}
