package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits.StringToString

object RWGAlenzaConfig {

  val cheJobInput=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.CheJobInput")
  val containerTerminalVisitActive=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.ContainerTerminalVisitActive")
  val vesselVisitDetails=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.VesselVisitDetails")
  val vesselVisit=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.VesselVisit")
  val invFcyUnitVisit=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.InvFcyUnitVisit")
}
