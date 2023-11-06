package platform.kafka

import app.MainAppServer.{keyGenericAvroSerde, valueGenericAvroSerde}
import config.AlenzaConfigLive
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.Produced
import platform.loader.InstanceLoader

class TopologyBuilder(instanceLoader: InstanceLoader) {

  private implicit val produced: Produced[GenericRecord, GenericRecord] =
    Produced.`with`(keyGenericAvroSerde, valueGenericAvroSerde)

  def build(outputTopic: String, customerName: String, tableName: String)(implicit builder: StreamsBuilder) = {
    val streamsBuilder = instanceLoader.getInstance(customerName, tableName)
    val streams        = streamsBuilder.result
    streams.to(outputTopic)
    builder.build()
  }

}


