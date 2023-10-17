package platform.kafka.avro

import com.sksamuel.avro4s.RecordFormat
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.scala.serialization.Serdes

import java.util.Collections
import scala.jdk.CollectionConverters._
/*
object AvroSerdes {

  val serdeConfig: java.util.Map[String, String] = Collections.singletonMap("schema.registry.url", "http://pac-sandbox-kafka.avlino.az:8081/") //"http://test01:8081/")

  val keyGenericAvroSerde = new GenericAvroSerde
  keyGenericAvroSerde.configure(serdeConfig, true) // `true` for record keys

  val valueGenericAvroSerde = new GenericAvroSerde
  valueGenericAvroSerde.configure(serdeConfig, false)

}*/
