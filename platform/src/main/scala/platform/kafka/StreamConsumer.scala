//package platform.kafka
//
//import config.{AlenzaConfig, AlenzaConfigLive}
//import org.apache.kafka.streams.KafkaStreams
//import org.apache.kafka.streams.scala.StreamsBuilder
//import platform.loader.{InstanceLoader, InstanceLoaderImpl}
//import platform.cache.{ConfigClassesCache, ConfigClassesCacheImpl}
//import zio.stream.ZStream
//import zio.{ZIO, ZLayer}
//
//import scala.reflect.ClassTag
//
//class StreamConsumer {
//
//  def start(outputTopic:String, customerName: String,
//            tableName: String): ZIO[ConsumerPropertyService  ,Throwable,KafkaStreams]={
//    implicit val streamsBuilder: StreamsBuilder = new StreamsBuilder()
//    val topologyBuilder = new TopologyBuilder(streamsBuilder)
//    for{
//      topology<-topologyBuilder.build(outputTopic,customerName,tableName)
//      properties<-ZIO.serviceWith[ConsumerPropertyService](_.getProperties)
//      streams= new KafkaStreams(topology,properties)
//      _=println("Starting kafka streams.........")
//      _=streams.start()
//    }yield streams
//  }.provideLayer(ZLayer.succeed[ConfigClassesCache](ConfigClassesCacheImpl)++
//    ZLayer.succeed[ConsumerPropertyService](ConsumerPropertyServiceImpl)++ZLayer.succeed[InstanceLoader](InstanceLoaderImpl)++ZLayer.succeed[AlenzaConfig](AlenzaConfigLive))
//}
//object StreamConsumerImpl extends StreamConsumer