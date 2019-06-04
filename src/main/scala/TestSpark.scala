import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._

object TestSpark {
  def main(args: Array[String]) {
    for (arg <- args) QueryClient.executeQuery(arg.toInt)
  }
}
