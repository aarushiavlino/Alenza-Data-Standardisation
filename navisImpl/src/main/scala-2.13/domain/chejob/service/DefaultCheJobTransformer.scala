package domain.chejob.service

import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import domain.chejob.entity.output.{CheJobOutputKey, CheJobOutputValue}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import config.RWGAlenzaConfig
import domain.chejob.entity.input.CheJobInput
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
class DefaultCheJobTransformer(builder:StreamsBuilder) extends KStreamsTransformer[CheJobOutputKey, CheJobOutputValue]{
  override def transform: KStream[CheJobOutputKey, CheJobOutputValue] = {
    val cheJobInputStream=builder.stream[String,CheJobInput](RWGAlenzaConfig.cheJobInput)

    cheJobInputStream.map{(k,v)=>(CheJobOutputKey(v.workInstructionKey,v.containerTerminalVisitKey),
      CheJobOutputValue(v.containerId,v.workInstructionKey,v.containerTerminalVisitKey,v.blockOfWork,v.bayOfWork,v.rowOfWork,v.tierOfWork,v.moveStage,v.moveType)
    ) }
  }

  override def getRecordFormat(key: CheJobOutputKey, value: CheJobOutputValue): (RecordFormat[CheJobOutputKey], RecordFormat[CheJobOutputValue]) =
    (RecordFormat[CheJobOutputKey], RecordFormat[CheJobOutputValue])
}
