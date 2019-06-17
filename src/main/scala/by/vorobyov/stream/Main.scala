package by.vorobyov.stream

import by.vorobyov.stream.Consumer.{batchProcesing, streamingProcessing}
import by.vorobyov.stream.Producer.writeToKafka
import by.vorobyov.stream.helper.PropertiesHelper.prop

import scala.util.Try

object Main {
  def main(args: Array[String]): Unit = {
    for (arg: String <- args) {
      if (arg.equals("batch")) batchProcesing()
      else if (arg.equals("stream")) streamingProcessing()
      else if (Try(arg.toInt).isSuccess) writeToKafka(prop.getProperty("topic"), arg.toInt)
    }
  }
}
