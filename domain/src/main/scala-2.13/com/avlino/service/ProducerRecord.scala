package com.avlino.service

import com.sksamuel.avro4s.RecordFormat

trait ProducerRecord[T, U] {
  def getRecordFormat(key: T, value: U): (RecordFormat[T], RecordFormat[U])
}