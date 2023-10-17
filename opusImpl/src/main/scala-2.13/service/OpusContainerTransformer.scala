package service

import com.avlino.tables.container.entity.{ContainerKey, ContainerValue}
import com.avlino.tables.container.service.ContainerTransformer
import com.goyeau.kafka.streams.circe.CirceSerdes.serde
import com.sksamuel.avro4s.RecordFormat
import config.OpusAlenzaConfig
import domain.{ OpusContainerEvent1, OpusContainerEvent2 }
import io.circe.generic.codec.DerivedAsObjectCodec.deriveCodec
import org.apache.kafka.streams.kstream.JoinWindows
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import zio.{ Task, ZIO }

import java.time
class OpusContainerTransformer(builder: StreamsBuilder) extends ContainerTransformer {

  override def transform: KStream[ContainerKey, ContainerValue] = {
    val inputTopic1 = OpusAlenzaConfig.inputTopic1
    val inputTopic2 = OpusAlenzaConfig.inputTopic2
    builder
      .stream[String, OpusContainerEvent1](inputTopic1)
      .leftJoin(builder.stream[String, OpusContainerEvent2](inputTopic2))(
        (left, right) => ContainerValue(left.value1, left.value2, right.value3, right.value4),
        JoinWindows.ofTimeDifferenceWithNoGrace(time.Duration.ofMinutes(1))
      )
      .map((key, value) => (ContainerKey(key), value))
  }

  override def getRecordFormat(key: ContainerKey,
                               value: ContainerValue): (RecordFormat[ContainerKey], RecordFormat[ContainerValue]) =
    (RecordFormat[ContainerKey], RecordFormat[ContainerValue])

}
