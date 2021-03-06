package by.vorobyov.spark

import org.apache.spark.sql.types._
import org.apache.spark.sql.{DataFrame, SparkSession}

object QueryClient {

  def executeQuery(taskNumber: Int): Unit = {
    val spark = getSparkContext()
    spark.sparkContext.setLogLevel("ERROR")
    val df = getDataFrame(spark)
    val query: Query = findQuery(taskNumber)
    query.execute(df)
    spark.close()
  }

  def getSparkContext() = {
    SparkSession.builder.master("local[*]").getOrCreate()
  }

  def getDataFrame(spark: SparkSession): DataFrame = {
    spark.read.format("csv")
      .schema(loadSchema())
      .load("src/main/input_data/train.csv")
  }

  def loadSchema(): StructType = {
    new StructType(Array(
      StructField("dateTime", StringType, nullable = true)
      , StructField("siteName", IntegerType, nullable = true)
      , StructField("posaContinent", IntegerType, nullable = true)
      , StructField("userLocationCountry", IntegerType, nullable = true)
      , StructField("userLocationRegion", IntegerType, nullable = true)
      , StructField("userLocationCity", IntegerType, nullable = true)
      , StructField("origDestinationDistance", DoubleType, nullable = true)
      , StructField("userId", IntegerType, nullable = true)
      , StructField("isMobile", IntegerType, nullable = true)
      , StructField("isPackage", IntegerType, nullable = true)
      , StructField("channel", IntegerType, nullable = true)
      , StructField("srchCi", StringType, nullable = true)
      , StructField("srchCo", StringType, nullable = true)
      , StructField("srchAdultsCnt", IntegerType, nullable = true)
      , StructField("srchChildrenCnt", IntegerType, nullable = true)
      , StructField("srchRmCnt", IntegerType, nullable = true)
      , StructField("srchDestinationId", IntegerType, nullable = true)
      , StructField("srchDestinationTypeId", IntegerType, nullable = true)
      , StructField("isBooking", IntegerType, nullable = true)
      , StructField("cnt", IntegerType, nullable = true)
      , StructField("hotelContinent", IntegerType, nullable = true)
      , StructField("hotelCountry", IntegerType, nullable = true)
      , StructField("hotelMarket", IntegerType, nullable = true)
      , StructField("hotelCluster", IntegerType, nullable = true)
    ))
  }

  def findQuery(taskNumber: Int): Query = {
    taskNumber match {
      case 1 => new FirstTaskQuery
      case 2 => new SecondTaskQuery
      case 3 => new ThirdTaskQuery
    }
  }
}
