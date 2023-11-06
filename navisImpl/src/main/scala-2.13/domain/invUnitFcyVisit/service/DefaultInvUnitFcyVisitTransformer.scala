package domain.invUnitFcyVisit.service

import com.avlino.service.KStreamsTransformer
import com.sksamuel.avro4s.RecordFormat
import config.RWGAlenzaConfig
import domain.invUnitFcyVisit.entity.input.InventoryFaciltyVisitInput
import domain.invUnitFcyVisit.entity.output.{InventoryFacilityVisitOutputKey, InventoryFacilityVisitOutputValue}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.scala.ImplicitConversions._
import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
class DefaultInvUnitFcyVisitTransformer(builder:StreamsBuilder) extends KStreamsTransformer[InventoryFacilityVisitOutputKey,InventoryFacilityVisitOutputValue]{
  override def transform: KStream[InventoryFacilityVisitOutputKey, InventoryFacilityVisitOutputValue] = {
    val inventoryFaciltyVisitInputStream=builder.stream[String,InventoryFaciltyVisitInput](RWGAlenzaConfig.invFcyUnitVisit)
      .filter((k,v)=>v.after.arrive_pos_loctype.equals("VESSEL"))
      .map((k,v)=>(v.after.gkey,v))

    inventoryFaciltyVisitInputStream.map[InventoryFacilityVisitOutputKey, InventoryFacilityVisitOutputValue]{(k,v)=>
      (
        InventoryFacilityVisitOutputKey(k),
        InventoryFacilityVisitOutputValue(k,v.after.visit_state,v.after.arrive_pos_loctype,
          v.after.arrive_pos_locid,
          v.after.arrive_pos_loc_gkey,v.after.actual_ib_cv,v.after.actual_ob_cv)
        )
    }
  }

  override def getRecordFormat(key: InventoryFacilityVisitOutputKey, value: InventoryFacilityVisitOutputValue):
  (RecordFormat[InventoryFacilityVisitOutputKey], RecordFormat[InventoryFacilityVisitOutputValue]) =
    (RecordFormat[InventoryFacilityVisitOutputKey], RecordFormat[InventoryFacilityVisitOutputValue])
}
