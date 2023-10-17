package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits.StringToString

object RWGAlenzaConfig {

  val cheJobInput=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Kafka.Topic.Src.CheJobInput")
  val containerTerminalVisitActive=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Kafka.Topic.Src.ContainerTerminalVisitActive")
  val vesselVisitDetails=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Kafka.Topic.Src.VesselVisitDetails")
}
