package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits._
class AlenzaConfig {

  lazy val table=System.getenv("TABLE")
  val outputTopic=ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"MVP.$table.Output")

}
object AlenzaConfigLive extends AlenzaConfig