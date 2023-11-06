package app

import com.avlino.alenza.kafka.AlenzaKafkaServer
import config.AlenzaConfigLive
import org.apache.kafka.streams.{StreamsBuilder, scala}
import platform.kafka.TopologyBuilder
import platform.loader.{InstanceLoaderImplWithConfig, InstanceLoaderImplWithRegistry}

object MainAppServer extends AlenzaKafkaServer{

  lazy val customer=System.getenv("CUSTOMER").toUpperCase
  lazy val table=System.getenv("TABLE").toLowerCase


  override protected def configureKafkaStreams(builder: StreamsBuilder): Unit = {
     implicit val  builderScala: scala.StreamsBuilder = builder.asScala
    new TopologyBuilder( InstanceLoaderImplWithRegistry)
      .build(AlenzaConfigLive.outputTopic(customer,table),customer,table)
  }

  override protected def getApplicationIdConfig: String = customer+".Standardised.MVP."+table+".server"
}
