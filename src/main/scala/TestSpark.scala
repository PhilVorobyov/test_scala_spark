import org.apache.spark.{SparkConf, SparkContext}

object TestSpark {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("HelloSpark")
      .setMaster("local[*]")
    val sc = new SparkContext(conf)
    sc.setLogLevel("Error")
    val textFile = sc.textFile("src/main/resources/test.txt", 3)
    val counts = textFile.flatMap(_.split(" "))
        .count()
    print(counts)
    sc.stop()
  }
}
