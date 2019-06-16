import java.util.Properties
import java.util.concurrent.ExecutorService

import by.vorobyov.stream.JsonGenerator
import org.apache.kafka.clients.producer._

object JsonProducer {

  def main(args: Array[String]): Unit = {
    writeToKafka("test1")
  }

  def writeToKafka(topic: String): Unit = {
    val producer = initializeProducer()
    val pool = java.util.concurrent.Executors.newFixedThreadPool(3)
    while (true) {
      sendJsonToKafka(producer, pool, topic)
    }
    producer.close()
  }

  def sendJsonToKafka(producer: KafkaProducer[String, String], pool: ExecutorService, topic: String): Unit = {
    Thread.sleep(1000)
    pool.execute(
    new Runnable {
        def run {
          val randomJson = JsonGenerator.generateJson()
          val record = new ProducerRecord(topic, "key" + randomJson, Thread.currentThread.getId + " :" + randomJson)
          producer.send(record)
        }
      }
    )
  }

  def initializeProducer(): KafkaProducer[String, String] = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }
}