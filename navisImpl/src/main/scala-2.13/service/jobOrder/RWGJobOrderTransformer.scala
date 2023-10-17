package service.jobOrder

import com.avlino.tables.jobOrder.entity.JobOrderValue
import com.avlino.tables.jobOrder.service.{JobOrderKey, JobOrderTransformer}
import com.sksamuel.avro4s.RecordFormat
import config.RWGAlenzaConfig
import domain.joborder.entity.input.{CheJobInput, ContainerTerminalVisitActiveInput, VesselVisitDetailsInput}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.kstream.KStream
import org.apache.kafka.streams.kstream.JoinWindows
import utils.CommonConstants._

import java.time.Duration
import avro.RecordFormats._
import com.avlino.avro.AvroSerdes._
import org.apache.kafka.streams.scala.ImplicitConversions._

import org.apache.kafka.streams.scala.serialization.Serdes.stringSerde
class RWGJobOrderTransformer(builder:StreamsBuilder) extends JobOrderTransformer {
  override def transform: KStream[JobOrderKey, JobOrderValue] = {
   cheJobInputStream.join(containerVisitStream)(cheJobContainerVisitJoiner,JoinWindows.ofTimeDifferenceWithNoGrace(Duration.ofSeconds(300)))
     .map[JobOrderKey,JobOrderValue] { (_, value) =>
       (JobOrderKey(value.workInstructionKey),
         JobOrderValue(value.containerId, value.containerVisitId, value.containerClass, value.blockOfWork, value.bayOfWork, value.rowOfWork, value.tierOfWork,
           value.containerWeight, value.containerIso, value.containerSize, None, None, None, value.containerSize,
           None, value.moveStage, value.moveType, value.portOfDischargeKey, None, None, None, None, None))
     }
  }

  val cheJobInputStream=builder.stream[String,CheJobInput](RWGAlenzaConfig.cheJobInput).map((k,v)=>(v.containerTerminalVisitKey,v))

  val containerVisitStream=builder.stream[String,ContainerTerminalVisitActiveInput](RWGAlenzaConfig.containerTerminalVisitActive).map((k,v)=>(v.containerTerminalVisitKey,v))

  val cheJobContainerVisitJoiner = (cheJob:CheJobInput,containerVisit:ContainerTerminalVisitActiveInput) =>
    CheJobContainerVisitJoiner(cheJob.containerId,containerVisit.containerTerminalVisitKey,containerVisit.containerCategory.getOrElse(UNKNOWN),
    cheJob.blockOfWork,cheJob.bayOfWork,cheJob.rowOfWork,cheJob.tierOfWork, containerVisit.goodsAndContainerWeightKg.getOrElse(-1),
      containerVisit.isoGroup,containerVisit.containerLengthFt.map(_.toString),
    cheJob.moveStage,cheJob.moveType,cheJob.workInstructionKey,containerVisit.portOfDischargeKey)


  val containerVisitVesselVisitJoiner = (containerVisit:ContainerTerminalVisitActiveInput,vesselVisit:VesselVisitDetailsInput) => ???


//  val vesselVisitDetailsStream=builder.stream[String,VesselVisitDetailsInput](RWGAlenzaConfig.vesselVisitDetails)
  override def getRecordFormat(key: JobOrderKey, value: JobOrderValue): (RecordFormat[JobOrderKey], RecordFormat[JobOrderValue]) =
    (RecordFormat[JobOrderKey], RecordFormat[JobOrderValue])

}

case class CheJobContainerVisitJoiner(containerId:String,containerVisitId:String,containerClass:String,
                                      blockOfWork:String,bayOfWork:String,rowOfWork:Option[Int],tierOfWork:Option[Int],
                                        containerWeight:Double,containerIso:String,containerSize:Option[String],
                                      moveStage:String,moveType:String,workInstructionKey:String, portOfDischargeKey: Option[String]
                                     )

case class ContainerVisitVesselVisitJoiner(vesselId:String, vesselTosKey:String,
                                           vesselArrivalDate: Option[String],
                                           vesselArrivalTime: Option[String],
                                           vesselDepartureDate: Option[String],
                                           vesselDepartureTime: Option[String],
                                           vesselBerthNumber: Option[String]
                                          )
