package com.avlino.service

import com.sksamuel.avro4s.Record
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.scala.kstream.KStream
import zio.{Task, ZIO}

trait KStreamsTransformer[K <: Product, V <: Product] extends ProducerRecord[K,V] {
  def transform: KStream[K, V]
  final def result= {
    transform.map[GenericRecord, GenericRecord]((k, v) => {
      println(s"----------------------Result is ${k} and ${v}")
      val formats = getRecordFormat(k, v)
      (formats._1.to(k), formats._2.to(v))
    })
  }
}
