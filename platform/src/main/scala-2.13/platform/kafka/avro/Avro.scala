package platform.kafka

import com.sksamuel.avro4s.RecordFormat
import com.softwaremill.tagging.@@



package object avro {
  type KeyRFTag
  type KeyRecordFormat[K] = RecordFormat[K] @@ KeyRFTag

  type ValueRFTag
  type ValueRecordFormat[V] = RecordFormat[V] @@ ValueRFTag
}