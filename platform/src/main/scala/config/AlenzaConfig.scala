package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits._
class AlenzaConfig {

  lazy val table=System.getenv("TABLE")
//  lazy val customer=System.getenv("CUSTOMER")
  val outputTopic=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"RWG.Standardised.$table.Output")

}
object AlenzaConfigLive extends AlenzaConfig