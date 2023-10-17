package platform.loader

import config.Config
import zio.Task
object RuntimeClassLoader {
  def loadFromConfig(customer:String,tableName: String):Class[_]= {

    val classNameHolder=Config.getConfig (customer+"."+tableName)
    val classLoaded=getClass.getClassLoader.loadClass(classNameHolder)
//    classLoaded.asSubclass(classDef)
     classLoaded
}

}
