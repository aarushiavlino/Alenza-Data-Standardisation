package domain.vesselVisit.service

import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import config.RWGAlenzaConfig
import domain.vesselVisit.entity.input.VesselVisitInput
import domain.vesselVisit.entity.output.{VesselVisitOutputKey, VesselVisitOutputValue}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import config.RWGAlenzaConfig
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
class DefaultVesselVisitTransformer (builder:StreamsBuilder) extends KStreamsTransformer[VesselVisitOutputKey,VesselVisitOutputValue]{
  override def transform: KStream[VesselVisitOutputKey, VesselVisitOutputValue] = {
    val vesselVisitStream=builder.stream[String,VesselVisitInput](RWGAlenzaConfig.vesselVisit).map((k,v)=>(v.tosKey,v))
    vesselVisitStream.map[VesselVisitOutputKey,VesselVisitOutputValue]{(k,v)=>
      (
        VesselVisitOutputKey(v.tosVisitDetailKey,v.id),
        VesselVisitOutputValue(v.tosKey,v.carrierMode,v.id,v.tosOperatorKey,v.tosVisitDetailKey)
        )
    }
  }

  override def getRecordFormat(key: VesselVisitOutputKey, value: VesselVisitOutputValue):
  (RecordFormat[VesselVisitOutputKey], RecordFormat[VesselVisitOutputValue]) =
    (RecordFormat[VesselVisitOutputKey], RecordFormat[VesselVisitOutputValue])
}
