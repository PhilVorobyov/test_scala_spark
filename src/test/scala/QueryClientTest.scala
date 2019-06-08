import by.vorobyov.QueryClient
import by.vorobyov.spark.{QueryClient, ThirdTaskQuery}
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types.StructType
import org.scalatest.FunSuite

class QueryClientTest extends FunSuite {

  test("QueryClient.findQuery") {
    assert(QueryClient.findQuery(3).isInstanceOf[ThirdTaskQuery])
  }

  test("QueryClient.loadSchema") {
    assert(QueryClient.loadSchema.isInstanceOf[StructType])
  }
}
