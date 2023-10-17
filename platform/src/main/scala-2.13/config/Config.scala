package config

import pureconfig.ConfigSource
import zio.{Task, ZIO}
import pureconfig.generic.auto._

import scala.reflect.ClassTag
object Config {
  def getConfig(path: String): String = ConfigSource.default.at(path).loadOrThrow[String]

}
