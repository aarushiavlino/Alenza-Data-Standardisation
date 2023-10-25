package avro

import com.avlino.avro.{KeyRFTag, KeyRecordFormat, ValueRFTag, ValueRecordFormat}
import com.sksamuel.avro4s.RecordFormat
import com.softwaremill.tagging.Tagger
import domain.chejob.entity.input.CheJobInput
import domain.containerTerminalvisit.entity.input.ContainerTerminalVisitActiveInput
import domain.invUnitFcyVisit.entity.input.InventoryFaciltyVisitInput
import domain.vesselVisit.entity.input.VesselVisitInput
import domain.vesselVisitDetails.entity.input.VesselVisitDetailsInput

object RecordFormats {

  implicit val cheJobRecordFormat: ValueRecordFormat[CheJobInput] = RecordFormat[CheJobInput].taggedWith[ValueRFTag]
  implicit val containerVisitRecordFormat: ValueRecordFormat[ContainerTerminalVisitActiveInput] = RecordFormat[ContainerTerminalVisitActiveInput].taggedWith[ValueRFTag]
  implicit val vesselVisitDetailsRecordFormat: ValueRecordFormat[VesselVisitDetailsInput] = RecordFormat[VesselVisitDetailsInput].taggedWith[ValueRFTag]
  implicit val vesselVisitRecordFormat: ValueRecordFormat[VesselVisitInput] = RecordFormat[VesselVisitInput].taggedWith[ValueRFTag]
  implicit val invFcyUnitRecordFormat: ValueRecordFormat[InventoryFaciltyVisitInput] = RecordFormat[InventoryFaciltyVisitInput].taggedWith[ValueRFTag]
//  implicit val vesselVisitRecordFormat: ValueRecordFormat[VesselVisitDetailsInput] = RecordFormat[VesselVisitDetailsInput].taggedWith[ValueRFTag]
}
