package com.avlino

import com.sksamuel.avro4s.{AvroSchema, RecordFormat}
import com.softwaremill.tagging.@@
import org.apache.avro.Schema

package object avro {
  type KeyRFTag
  type KeyRecordFormat[K] = RecordFormat[K] @@ KeyRFTag

  type ValueRFTag
  type ValueRecordFormat[V] = RecordFormat[V] @@ ValueRFTag
}
