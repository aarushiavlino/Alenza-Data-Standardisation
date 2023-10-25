package registry

import com.avlino.service.KStreamsTransformer
import domain.chejob.service.DefaultCheJobTransformer
import domain.containerTerminalvisit.service.DefaultContainerTerminalVisitTransformer
import org.apache.kafka.streams.scala.StreamsBuilder
import Constants._
import domain.invUnitFcyVisit.service.DefaultInvUnitFcyVisitTransformer
import domain.vesselVisit.service.DefaultVesselVisitTransformer
import domain.vesselVisitDetails.service.DefaultVesselVisitDetailsTransformer
object ProductRegistry {
  private val navis: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]] = Map(
    Table.CHE_JOB -> ((builder: StreamsBuilder) => new DefaultCheJobTransformer(builder)),
    Table.CONTAINER_TERMINAL_VISIT_ACTIVE -> ((builder: StreamsBuilder) => new DefaultContainerTerminalVisitTransformer(builder)),
    Table.VESSEL_VISIT -> ((builder: StreamsBuilder) => new DefaultVesselVisitTransformer(builder)),
    Table.VESSEL_VISIT_DETAILS -> ((builder: StreamsBuilder) => new DefaultVesselVisitDetailsTransformer(builder)),
    Table.INV_FCY_UNIT_VISIT -> ((builder: StreamsBuilder) => new DefaultInvUnitFcyVisitTransformer(builder)),
  )

  private val rwg: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]] = Map(
    Table.CHE_JOB -> ((builder: StreamsBuilder) => new DefaultCheJobTransformer(builder)),
    Table.CONTAINER_TERMINAL_VISIT_ACTIVE -> ((builder: StreamsBuilder) => new DefaultContainerTerminalVisitTransformer(builder)),
    Table.VESSEL_VISIT -> ((builder: StreamsBuilder) => new DefaultVesselVisitTransformer(builder)),
    Table.VESSEL_VISIT_DETAILS -> ((builder: StreamsBuilder) => new DefaultVesselVisitDetailsTransformer(builder)),
    Table.INV_FCY_UNIT_VISIT -> ((builder: StreamsBuilder) => new DefaultInvUnitFcyVisitTransformer(builder)),
  )

  private val opus: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]]=Map.empty

  def get(customer: String, table: String)(implicit builder: StreamsBuilder) = { //: ZIO[Any, Any, _ <: KStreamTransformer[] ]= {
    val impl = customer match {
      case Customer.NAVIS => navis(table).apply(builder)
      case Customer.OPUS => opus(table).apply(builder)
      case Customer.RWG => rwg(table).apply(builder)
    }
    impl
  }
}
