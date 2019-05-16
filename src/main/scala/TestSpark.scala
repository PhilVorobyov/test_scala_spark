import org.apache.spark.{SparkConf, SparkContext}

object TestSpark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("HelloSpark")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)

    println("hello spark")

    sc.stop()
  }
}
