package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits._
class AlenzaConfig {

  //  lazy val table=System.getenv("TABLE")
  //  lazy val customer=System.getenv("CUSTOMER")
  def outputTopic(customerName: String, tableName: String) = ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"$customerName.Standardised.Kafka.Topic.Dst.$tableName")

  //  def scriptUrl(customerName:String,tableName:String)= ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"$customerName.Standardised.GroovyScript.URL.$tableName")
//  def scriptUrl(customerName: String, tableName: String) = "navisImpl\\src\\main\\resources"
}
object AlenzaConfigLive extends AlenzaConfig