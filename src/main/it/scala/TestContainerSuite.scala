package it

class TestContainerSuite extends BaseSpec with Containers with KafkaHelper with TestContainersSpec{

  "Test container" must{
    behave like testPlatformHolisitcTests()
  }

}
