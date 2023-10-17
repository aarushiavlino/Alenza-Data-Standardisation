//import domain.container.ContainerValue
//import domain.{OpusContainerEvent1, OpusContainerEvent2}
//import platform.loader.InstanceLoaderImpl
//import platform.cache.{ConfigClassesCache, ConfigClassesCacheImpl}
//import transformer.OpusCombineEvent
//import zio._
//import zio.test._
//
//object InstanceLoaderSpec$ extends ZIOSpecDefault {
//  override def spec: Spec[TestEnvironment with Scope, Any] = suite("ExceutorSpec")(
//    test("execute opus container transformer"){
//
//      val zioOutput= InstanceLoaderImpl.getInstance[OpusCombineEvent,ContainerValue]("opus",
//        OpusCombineEvent(OpusContainerEvent1("a1"), OpusContainerEvent2("a2")))
//
//      for{
//        output<-zioOutput
//      }yield assertTrue(output.key=="a1_a2")
//    }
//  ).provideLayer(zlayer)
//
//  val zlayer:ZLayer[TestEnvironment with Scope, Any,ConfigClassesCache]= ZLayer.succeed(ConfigClassesCacheImpl)
//}
