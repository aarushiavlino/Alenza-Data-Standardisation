package domain.vesselVisitDetails.service

import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import config.RWGAlenzaConfig
import domain.vesselVisitDetails.entity.input.VesselVisitDetailsInput
import domain.vesselVisitDetails.entity.output.{VesselVisitDetailsOutputKey, VesselVisitDetailsOutputValue}
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import utils.CommonConstants.UNKNOWN
import utils.DateTimeUtils._

import java.text.SimpleDateFormat
import java.util.Date

class DefaultVesselVisitDetailsTransformer(builder:StreamsBuilder) extends KStreamsTransformer[VesselVisitDetailsOutputKey,VesselVisitDetailsOutputValue]{
  override def transform(groovyScriptEngine: GroovyScriptEngine,binding: Binding): KStream[VesselVisitDetailsOutputKey, VesselVisitDetailsOutputValue] = {
    val vesselVisitDetailsStream=builder.stream[String,VesselVisitDetailsInput](RWGAlenzaConfig.vesselVisitDetails)

    vesselVisitDetailsStream.map[VesselVisitDetailsOutputKey, VesselVisitDetailsOutputValue]{(k,v)=>
      (
        VesselVisitDetailsOutputKey(v.tosKey),
        VesselVisitDetailsOutputValue(v.tosKey,v.vesselKey.getOrElse(UNKNOWN),v.publishedEta.map(millisToDate),
          v.publishedEta.map(millisToTime),v.publishedEtd.map(millisToDate),
          v.publishedEtd.map(millisToTime),v.berthingPlans.map(_.berthingKey))
        )
    }
  }

  override def getRecordFormat(key: VesselVisitDetailsOutputKey, value: VesselVisitDetailsOutputValue):
  (RecordFormat[VesselVisitDetailsOutputKey], RecordFormat[VesselVisitDetailsOutputValue]) =
    (RecordFormat[VesselVisitDetailsOutputKey], RecordFormat[VesselVisitDetailsOutputValue])
}
