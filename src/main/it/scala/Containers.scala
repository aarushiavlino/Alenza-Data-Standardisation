trait Containers extends BaseContainers{
        self:Suite=>

        override type Containers=KafkaContainer and GenericContainer

protected lazy val serviceContainer:GenericContainer=baseAppContainer(
        name="MainApp",
        jarName="run.jar",
        mainClass="app.MainApp2",
        baseFolder="target/scala-2.13",
        envVars=Map(
        "ENVIRONMENT"->"local",
        ),
        containerDependencies=List(kafkaContainer)
        )
        override def startContainers:Containers={
        Startables.deepStart(kafkaContainer).join()
        serviceContainer.start()
        kafkaContainer and serviceContainer
        }


        }
