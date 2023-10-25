import com.dimafeng.testcontainers.{GenericContainer, KafkaContainer}
import com.dimafeng.testcontainers.lifecycle.and
import it.BaseContainers
import org.scalatest.Suite
import org.testcontainers.lifecycle.Startables

trait Containers extends BaseContainers {
  self: Suite =>

  override type Containers = KafkaContainer and GenericContainer

  protected lazy val serviceContainer: GenericContainer = baseAppContainer(
    name = "MainApp",
    jarName = "run.jar",
    mainClass = "app.MainAppServer1",
    baseFolder = "platform/target/scala-2.13",
    envVars = Map(
      "ENVIRONMENT" -> "local",
    ),
    containerDependencies = List(kafkaContainer)
  )
  override def startContainers: Containers = {
    Startables.deepStart(kafkaContainer).join()
    serviceContainer.start()
    kafkaContainer and serviceContainer
  }

}
