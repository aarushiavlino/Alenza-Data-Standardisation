//package domain
//
//import domain.service.ContainerTransformer
//
//object TableToEntityMapping{
//import TableNameConstants._
//  def getEntity(tableName:String):Class[_] ={
//    tableName match {
//      case CONTAINER => classOf[ContainerTransformer]
////      case _ => classOf[_]
//    }
//  }
//
//}
//object TableNameConstants{
//  final val CONTAINER="CONTAINER".toLowerCase
//  final val CONTAINER_VISIT="CONTAINER_VISIT"
//}