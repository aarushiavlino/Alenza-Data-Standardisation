package platform.kafka

import java.util.Properties

class ConsumerPropertyService {
  def getProperties: Properties = {
    val props = new Properties()
    props.put("application.id", "testing")
    props.put("bootstrap.servers", "pac-sandbox-kafka.avlino.az:9092")
    props.put("kafka.bootstrap.servers", "pac-sandbox-kafka.avlino.az:9092")
    props.put("schema.registry.url", "http://pac-sandbox-kafka.avlino.az:8081/")
    props.put("replication.factor",1)
    props
  }
}
object ConsumerPropertyServiceImpl extends ConsumerPropertyService
