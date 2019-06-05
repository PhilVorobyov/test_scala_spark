package by.vorobyov

object TestSpark {
  def main(args: Array[String]) {
    for (arg <- args) QueryClient.executeQuery(arg.toInt)
  }
}
