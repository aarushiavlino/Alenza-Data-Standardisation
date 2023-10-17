package avro

import com.avlino.avro.{KeyRFTag, KeyRecordFormat, ValueRFTag, ValueRecordFormat}
import com.sksamuel.avro4s.RecordFormat
import com.softwaremill.tagging.Tagger
import domain.joborder.entity.input.{CheJobInput, ContainerTerminalVisitActiveInput, VesselVisitDetailsInput}

object RecordFormats {

  implicit val cheJobRecordFormat: ValueRecordFormat[CheJobInput] = RecordFormat[CheJobInput].taggedWith[ValueRFTag]
  implicit val containerVisitRecordFormat: ValueRecordFormat[ContainerTerminalVisitActiveInput] = RecordFormat[ContainerTerminalVisitActiveInput].taggedWith[ValueRFTag]
  implicit val vesselVisitRecordFormat: ValueRecordFormat[VesselVisitDetailsInput] = RecordFormat[VesselVisitDetailsInput].taggedWith[ValueRFTag]
}
