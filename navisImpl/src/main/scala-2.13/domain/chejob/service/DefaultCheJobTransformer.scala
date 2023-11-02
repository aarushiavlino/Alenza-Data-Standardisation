package domain.chejob.service

import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import config.RWGAlenzaConfig
import domain.chejob.entity.input.CheJobInput
import domain.chejob.entity.output.{ CheJobOutputKey, CheJobOutputValue }
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
class DefaultCheJobTransformer(builder: StreamsBuilder, customerName: String, tableName: String)
    extends KStreamsTransformer[CheJobOutputKey, CheJobOutputValue] {
  val inputTopicName = RWGAlenzaConfig.inputTopic(customerName, tableName)
  val keyScript      = RWGAlenzaConfig.keyScriptName(customerName, tableName)
  val valueScript    = RWGAlenzaConfig.valueScriptName(customerName, tableName)
  override def transform(groovyScriptEngine: GroovyScriptEngine,
                         binding: Binding): KStream[CheJobOutputKey, CheJobOutputValue] = {
    val cheJobInputStream = builder.stream[String, CheJobInput](inputTopicName)

    cheJobInputStream.map { (k, v) =>
      binding.setProperty("cheJobInputKey", k)
      binding.setProperty("cheJobInputValue", v)
      (
        groovyScriptEngine.run(keyScript, binding).asInstanceOf[CheJobOutputKey],
        groovyScriptEngine.run(valueScript, binding).asInstanceOf[CheJobOutputValue]
      )
//      (CheJobOutputKey(v.workInstructionKey,v.containerTerminalVisitKey),
//      CheJobOutputValue(v.containerId,v.workInstructionKey,v.containerTerminalVisitKey,v.blockOfWork,v.bayOfWork,v.rowOfWork,v.tierOfWork,v.moveStage,v.moveType)
    }
  }

  override def getRecordFormat(
      key: CheJobOutputKey,
      value: CheJobOutputValue
  ): (RecordFormat[CheJobOutputKey], RecordFormat[CheJobOutputValue]) =
    (RecordFormat[CheJobOutputKey], RecordFormat[CheJobOutputValue])
}
