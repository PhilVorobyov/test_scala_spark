package by.vorobyov.stream

import org.apache.kafka.clients.producer.KafkaProducer
import org.scalatest.FunSuite

class ProducerTest extends FunSuite {

  test("testInitializeProducer") {
    assert(Producer.initializeProducer().isInstanceOf[KafkaProducer[String, String]])
  }
}
