package by.vorobyov.stream

import java.util.concurrent.TimeUnit

import by.vorobyov.stream.helper.PropertiesHelper.prop
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, Mode, OutputTimeUnit}

/** receive kafka messages. */
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.Throughput))
object Consumer {

  @Benchmark
  def batch(): Unit = batchProcesing()

  @Benchmark
  def stream(): Unit = streamingProcessing()

  def batchProcesing() = {
    val spark = initializeSparkSession()
    val inputDf = spark.read
      .format("kafka")
      .option("kafka.bootstrap.servers", prop.getProperty("bootstrap_servers"))
      .option("subscribe", prop.getProperty("topic"))
      .load()

    val df = inputDf.toDF().coalesce(1)
    df.write.mode(SaveMode.Overwrite).parquet(prop.getProperty("hdfs_server") + "/user/hdfs/batch")
  }

  def streamingProcessing() = {
    val spark = initializeSparkSession()
    val inputDf = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", prop.getProperty("bootstrap_servers"))
      .option("subscribe", prop.getProperty("topic"))
      .load()

    val query = inputDf
      .writeStream
      .outputMode("append")
      .format("parquet")
      .queryName("lbcautomation")
      .option("checkpointLocation", prop.getProperty("hdfs_server") + "/test/")
      .option("path", prop.getProperty("hdfs_server") + "/user/hdfs/stream")
      .option("failOnDataLoss", "false")
      .start()
      .awaitTermination()
  }

  private def initializeSparkSession() = {
    SparkSession.builder()
      .master("local[*]")
      .appName("StructuredApp")
      .getOrCreate()
  }
}
