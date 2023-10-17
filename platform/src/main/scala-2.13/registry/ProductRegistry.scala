package registry

import com.avlino.service.KStreamsTransformer
import org.apache.kafka.streams.scala.StreamsBuilder
import service.OpusContainerTransformer
import service.jobOrder.RWGJobOrderTransformer

object ProductRegistry {
  private val navis: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]] = Map(
//    "container" -> ((builder: StreamsBuilder) => new OpusContainerTransformer(builder))
    "joborder" -> ((builder: StreamsBuilder) => new RWGJobOrderTransformer(builder))

  )

  private val rwg: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]] = Map(
    //    "container" -> ((builder: StreamsBuilder) => new OpusContainerTransformer(builder))
    "joborder" -> ((builder: StreamsBuilder) => new RWGJobOrderTransformer(builder))

  )
  private val opus: Map[String, StreamsBuilder => KStreamsTransformer[_ <: Product, _ <: Product]] =
    Map("container" -> ((builder: StreamsBuilder) => new OpusContainerTransformer(builder)))

  def get(customer: String, table: String)(implicit builder: StreamsBuilder) = { //: ZIO[Any, Any, _ <: KStreamTransformer[] ]= {
    val impl = customer match {
      case "navis" => navis(table).apply(builder)
      case "opus" => opus(table).apply(builder)
      case "rwg" => rwg(table).apply(builder)
    }
    impl
  }
}
