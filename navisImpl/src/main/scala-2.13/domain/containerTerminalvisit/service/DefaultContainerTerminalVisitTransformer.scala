package domain.containerTerminalvisit.service

import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import domain.containerTerminalvisit.entity.output.{ContainerTerminalVisitOutputKey, ContainerTerminalVisitOutputValue}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import utils.CommonConstants._

import java.time.Duration
import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import config.RWGAlenzaConfig
import domain.containerTerminalvisit.entity.input.ContainerTerminalVisitActiveInput
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
class DefaultContainerTerminalVisitTransformer (builder:StreamsBuilder) extends KStreamsTransformer[ContainerTerminalVisitOutputKey, ContainerTerminalVisitOutputValue]{
  override def transform: KStream[ContainerTerminalVisitOutputKey, ContainerTerminalVisitOutputValue] = {
    val containerVisitStream=builder.stream[String,ContainerTerminalVisitActiveInput](RWGAlenzaConfig.containerTerminalVisitActive)
      .filterNot((k,v)=>v==null)
      .map((k,v)=>(v.containerTerminalVisitKey,v))

    containerVisitStream.map[ContainerTerminalVisitOutputKey,ContainerTerminalVisitOutputValue]{(k,v)=>
      (ContainerTerminalVisitOutputKey(v.containerTerminalVisitKey),
      ContainerTerminalVisitOutputValue(v.containerTerminalVisitKey,v.containerId.getOrElse(UNKNOWN),
        v.containerCategory.getOrElse(UNKNOWN),v.goodsAndContainerWeightKg.getOrElse(-1),v.isoSizeType,v.isoGroup,
        None,None,None,None,v.containerLengthFt.map(_.toString),v.portOfDischargeKey)
      )
    }
  }

  override def getRecordFormat(key: ContainerTerminalVisitOutputKey, value: ContainerTerminalVisitOutputValue):
  (RecordFormat[ContainerTerminalVisitOutputKey], RecordFormat[ContainerTerminalVisitOutputValue]) =
    (RecordFormat[ContainerTerminalVisitOutputKey], RecordFormat[ContainerTerminalVisitOutputValue])
}
