package platform.cache

import zio.ZIO

import scala.collection.mutable

class ConfigClassesCache {
  val objectsMap: mutable.HashMap[String, Any] = mutable.HashMap.empty

  def checkInCache(customer: String, inputClassName: String) =
    objectsMap.get(keyBuilder(customer, inputClassName))

  private def keyBuilder(customer: String, inputClassName: String) = customer + "." + inputClassName

  def putInCache(customer: String, inputClassName: String, instance: Any) =
 objectsMap.put(keyBuilder(customer, inputClassName), instance)

}
object ConfigClassesCacheImpl extends ConfigClassesCache
