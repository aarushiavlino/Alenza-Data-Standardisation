package platform.loader
import com.avlino.service.KStreamsTransformer
import org.apache.kafka.streams.scala.StreamsBuilder
import platform.cache.ConfigClassesCache
import registry.ProductRegistry

trait InstanceLoader {
  def getInstance(customer: String, tableName: String)(
      implicit builder: StreamsBuilder
  ): KStreamsTransformer[_ <: Product, _ <: Product]
}
object InstanceLoaderImplWithRegistry extends InstanceLoader {
  def getInstance(customer: String, tableName: String)(
      implicit builder: StreamsBuilder
  ): KStreamsTransformer[_ <: Product, _ <: Product] =
    ProductRegistry.get(customer.toUpperCase, tableName.toLowerCase)(builder)
}



object InstanceLoaderImplWithConfig extends InstanceLoader{

  val cacheService= new ConfigClassesCache

  def getInstance(customer: String, tableName: String)(
    implicit builder: StreamsBuilder
  ): KStreamsTransformer[_ <: Product, _ <: Product] = {

      val classInstance = RuntimeClassLoader.loadFromConfig(customer, tableName)
      val instanceOpt = cacheService.checkInCache(customer, tableName)
      val instance = if (instanceOpt.isEmpty) {
        val newInstance = classInstance.getDeclaredConstructor().newInstance()
        cacheService.putInCache(customer, tableName, newInstance)
        newInstance
      } else instanceOpt.get
    instance.asInstanceOf[KStreamsTransformer[_ <: Product, _ <: Product]]
  }
}
