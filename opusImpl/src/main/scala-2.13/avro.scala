import com.sksamuel.avro4s.{AvroSchema, RecordFormat}
import com.softwaremill.tagging.{@@, Tagger}
import domain.{ OpusContainerEvent1, OpusContainerEvent2}
import org.apache.avro.Schema

package object avro {
  type KeyRFTag
  type KeyRecordFormat[K] = RecordFormat[K] @@ KeyRFTag

  type ValueRFTag
  type ValueRecordFormat[V] = RecordFormat[V] @@ ValueRFTag

  implicit val containerValueRF: ValueRecordFormat[OpusContainerEvent1] = RecordFormat[OpusContainerEvent1].taggedWith[ValueRFTag]
  implicit val containerValueRF2: ValueRecordFormat[OpusContainerEvent2] = RecordFormat[OpusContainerEvent2].taggedWith[ValueRFTag]
  implicit val containerKeyRF: KeyRecordFormat[String] = RecordFormat[String].taggedWith[KeyRFTag]
  val containerKeySchema: Schema = AvroSchema[OpusContainerEvent1]
}
