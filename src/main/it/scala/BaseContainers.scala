package it

import com.dimafeng.testcontainers.KafkaContainer
import com.dimafeng.testcontainers.scalatest.TestContainersForAll
import org.scalatest.Suite
import org.testcontainers.containers.Network
import org.testcontainers.utility.DockerImageName
import com.dimafeng.testcontainers._
import BaseContainers.{AppPort, WaitStrategyForService}
import org.testcontainers.containers.wait.strategy.{HttpWaitStrategy, LogMessageWaitStrategy, WaitAllStrategy, WaitStrategy}
import org.testcontainers.images.builder.ImageFromDockerfile
import org.testcontainers.lifecycle.Startable
import org.testcontainers.containers.{Network, GenericContainer => JavaGenericContainer}

import scala.jdk.CollectionConverters._
import java.nio.file.Path
trait BaseContainers extends TestContainersForAll{
  self: Suite =>

  protected val sharedNetwork: Network = Network.newNetwork()
  protected lazy val kafkaContainer =new KafkaContainer(
    DockerImageName.parse("confluentinc/cp-kafka:7.4.0")) {
    container.withNetwork(sharedNetwork)
    container.withNetworkAliases("kafka")
  }

  protected def baseAppContainer(
                                name:String,
                                  jarName:String,
                                    mainClass:String,
                                      baseFolder:String,
                                        port:Int = AppPort,
                                          envVars:Map[String,String] = Map.empty,
                                            containerDependencies:List[Startable]
                                ):GenericContainer = new GenericContainer({
    val c= new JavaGenericContainer(
      new ImageFromDockerfile()
        .withFileFromPath(jarName, Path.of(s"$baseFolder/$jarName").toAbsolutePath)
        .withDockerfileFromBuilder { builder =>
          builder
            .from("openjdk:11-jre-slim")
            .copy(jarName, s"/$jarName")
            .cmd("java", "-Xmx256m", "-cp", s"/$jarName", mainClass)
            .build()
        }
    )
    c.dependsOn(containerDependencies.asJavaCollection)
    c.withEnv(envVars.asJava)
    c.withStartupAttempts(3)
    c.withEnv("ENVIRONMENT", "local")
    c.withEnv("APP_PORT", port.toString)
    c.withEnv("KAFKA_BOOTSTRAP_SERVERS", "kafka:9092")
    c.setWaitStrategy(WaitStrategyForService)
    c.withExposedPorts(port)
    c.withNetwork(sharedNetwork)
    c.withNetworkAliases(name)
    c
  })
}
object BaseContainers{
  val AppPort = 8080
  val WaitStrategyForAPI: WaitStrategy = new HttpWaitStrategy().forPath("/health").forStatusCode(200)

  val WaitStrategyForService: WaitStrategy = new WaitAllStrategy()
    .withStrategy(WaitStrategyForAPI)
    .withStrategy(new LogMessageWaitStrategy().withRegEx("^.*Resetting offset for partition.*$"))
}