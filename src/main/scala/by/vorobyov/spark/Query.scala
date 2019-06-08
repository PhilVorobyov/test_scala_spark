package by.vorobyov.spark

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.desc

trait Query {
  def execute(df: DataFrame) {}
}

/** First Spark task.
  *
  * Find top 3 most popular hotels between couples.
  * (treat hotel as composite key of continent, country and market).
  */
class FirstTaskQuery extends Query {
  override def execute(df: DataFrame): Unit = {
    df.filter("srchAdultsCnt == 2 AND srchRmCnt == 1")
      .groupBy("posaContinent", "userLocationCountry", "hotelMarket")
      .count()
      .orderBy(desc("count"))
      .show(3) // better to use head, this choose for simplicity of view
  }
}

/** Second Spark task
  *
  * Find the most popular country where hotels are booked and searched from the same country.
  */
class SecondTaskQuery extends Query {
  override def execute(df: DataFrame): Unit = {
    df.filter("hotelCountry == userLocationCountry AND isBooking == 1")
      .groupBy("hotelCountry")
      .count()
      .orderBy(desc("count"))
      .show(1) // better to use head, this choose for simplicity of view
  }
}

/** Third Spark task
  *
  * Find top 3 hotels where people with children are interested but not booked in the end.
  */
class ThirdTaskQuery extends Query {
  override def execute(df: DataFrame): Unit = {
    df.filter("srchChildrenCnt > 0 AND isBooking == 0")
      .groupBy("hotelMarket")
      .count()
      .orderBy(desc("count"))
      .show(3) // better to use head, this choose for simplicity of view
  }
}
