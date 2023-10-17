package config

import com.avlino.alenza.config.ConfigLoader
import com.avlino.alenza.core.implicits._
object OpusAlenzaConfig  {

  val inputTopic1= ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"Opus.Kafka.Topic.Src.Event1")
  val inputTopic2= ConfigLoader().getRequiredConfigValueAndFailIfMissingOrInvalid[String](s"Opus.Kafka.Topic.Src.Event2")

}