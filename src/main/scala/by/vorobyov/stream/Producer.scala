package by.vorobyov.stream

import java.util.Properties
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors.newFixedThreadPool

import by.vorobyov.stream.helper.JsonGenerator
import by.vorobyov.stream.helper.PropertiesHelper.prop
import org.apache.kafka.clients.producer._

/** write messages to kafka. */
object Producer {

  def writeToKafka(topic: String, numberOfThreads: Int): Unit = {
    val producer = initializeProducer()
    val pool = newFixedThreadPool(numberOfThreads)
    while (true) {
      sendJsonToKafka(producer, pool, topic)
    }
    producer.close()
  }

  private def sendJsonToKafka(producer: KafkaProducer[String, String], pool: ExecutorService, topic: String): Unit = {
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
    props.put("bootstrap.servers", prop.getProperty("bootstrap_servers"))
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    new KafkaProducer[String, String](props)
  }
}