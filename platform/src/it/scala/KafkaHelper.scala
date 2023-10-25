import org.apache.kafka.clients.consumer.Consumer
import org.apache.kafka.clients.producer.KafkaProducer
import zio.ZLayer

import java.util.Properties

trait KafkaHelper {
  private val BOOSTRAP_SERVERS = List("localhost:29092")
  private val KAFKA_TOPIC      = "streaming-hello"
  protected def kafkaProducer[K, V](): KafkaProducer[K, V] = {
    val properties = new Properties()
    properties.put("bootstrap.servers", BOOSTRAP_SERVERS)

    new KafkaProducer[K, V](properties)

  }

/*  protected def kafkaConsumer[T] =
    ZLayer.scoped(
      Consumer.consumeWith(
        settings = ConsumerSettings(BOOSTRAP_SERVERS)
          .withGroupId("streaming-kafka-app")
          .withBootstrapServers(BOOSTRAP_SERVERS),
        subscription = Subscription.topics(KAFKA_TOPIC),
        keyDeserializer = Serde.string,
        valueDeserializer = Serde.string
      )(record => Console.printLine((record.key(), record.value())).orDie)
    )*/

}
