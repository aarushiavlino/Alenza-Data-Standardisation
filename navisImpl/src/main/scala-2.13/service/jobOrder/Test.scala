package service.jobOrder

import com.avlino.tables.jobOrder.entity.JobOrderValue
import com.avlino.tables.jobOrder.service.JobOrderKey
import com.sksamuel.avro4s.AvroSchema

object Test extends App{

    val actionResponseValueschema = AvroSchema[JobOrderValue]
    println(actionResponseValueschema.toString)
}
