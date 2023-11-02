package com.avlino.service

import com.sksamuel.avro4s.Record
import groovy.lang.Binding
import groovy.util.GroovyScriptEngine
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.streams.scala.kstream.KStream
import zio.{Task, ZIO}

trait Simple
trait KStreamsTransformer[K, V] extends ProducerRecord[K,V] {
  def transform(groovyScriptEngine: GroovyScriptEngine,binding: Binding): KStream[K, V]
  final def result(groovyScriptEngine: GroovyScriptEngine,binding: Binding)= {
    transform(groovyScriptEngine,binding).map[GenericRecord, GenericRecord]((k, v) => {
      println(s"----------------------Result is ${k} and ${v}")
      val formats = getRecordFormat(k, v)
      (formats._1.to(k), formats._2.to(v))
    })
  }
}
