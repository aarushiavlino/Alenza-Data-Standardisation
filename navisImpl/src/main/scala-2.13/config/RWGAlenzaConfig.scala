package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits.StringToString

object RWGAlenzaConfig {

  def inputTopic(customerName:String,tableName:String)=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"$customerName.Standardised.Kafka.Topic.Src.$tableName")
 def keyScriptName(customerName:String,tableName:String)=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"$customerName.Standardised.GroovyScript.Key.$tableName")
 def valueScriptName(customerName:String,tableName:String)=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"$customerName.Standardised.GroovyScript.Value.$tableName")


  val containerTerminalVisitActive=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.ContainerTerminalVisitActive")
  val vesselVisitDetails=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.VesselVisitDetails")
  val vesselVisit=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.VesselVisit")
  val invFcyUnitVisit=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String]("RWG.Standardised.Kafka.Topic.Src.InvFcyUnitVisit")
}
