trait TestContainersSpec{
        _:BaseSpec with Containers with KafkaHelper=>

        def testPlatformHolisitcTests()=
        "Platform Holistic Tests"must{
        "On producing data to input stream, data in output should be available "in{
        val opusContainerEvent1="OpusContainerEvent1(A,B,C)"
        val topic="Raw-Opus-Input"
        val producerRecord=new ProducerRecord[String,String](topic,"1",opusContainerEvent1)
        kafkaProducer[String,String]().send(producerRecord)
        }
        }

        }
