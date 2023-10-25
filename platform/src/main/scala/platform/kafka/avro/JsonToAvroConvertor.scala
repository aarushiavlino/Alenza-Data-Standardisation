package platform.kafka.avro

import org.apache.avro.{AvroRuntimeException, Schema}
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.io.DatumReader

import java.io.IOException

object JsonToAvroConvertor {

  import org.apache.avro.io.DecoderFactory
  private val decoderFactory = DecoderFactory.get


  def jsonToAvro(jsonString: String, schema: Schema): Any = {
    try {
      val reader: DatumReader[AnyRef] = new GenericDatumReader[AnyRef](schema)
      val avroObject: Any = reader.read(null, decoderFactory.jsonDecoder(schema, jsonString))
      if (schema.getType.equals(Schema.Type.STRING)) avroObject.toString
      else avroObject
    } catch {
      case e: IOException =>
        throw new IOException(String.format("Error deserializing json %s to Avro of schema %s", jsonString, schema), e)
      case e: AvroRuntimeException =>
        throw new AvroRuntimeException(String.format("Error deserializing json %s to Avro of schema %s", jsonString, schema), e)
    }
  }
}