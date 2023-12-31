import sbt.*
object Dependencies {
  object Versions {
    val kafkaVersion      = "3.3.0"
    val pureConfig        = "0.17.4"
    val cats              = "2.5.0"
    val catsEffect        = "2.4.1"
    val fs2               = "2.5.4"
    val avro              = "1.9.0"
    val alenzaKafkaServer = "0.1.Snapshot.137"
    val zioKafka          = "2.4.3"
    val zio               = "2.0.18"
    val alenzaConfig      = "0.1.Snapshot-Test"
    val alenzakafkaserver      = "0.1.Snapshot-Test"
    val circe             = "0.13.0"
    val kafkaAvro         = "7.4.0"
    val avro4s            = "4.1.1"
    val smlCommon         = "2.3.1"
//    val circeVersion = "0.14.1"
    val kafkaStreamsCirce ="fbee94b"

  }

  private def commonTestDependencies = Seq(
    "org.scalatest" %% "scalatest" % "3.2.15",
  )

  lazy val testAndITTestDependencies = (commonTestDependencies ++ Seq(
    "com.dimafeng" %% "testcontainers-scala" % "0.40.15",
    "com.dimafeng" %% "testcontainers-scala-mysql" % "0.40.15",
    "com.dimafeng" %% "testcontainers-scala-kafka" % "0.40.15",
    "com.dimafeng" %% "testcontainers-scala-mockserver" % "0.40.15",
    "org.mock-server" % "mockserver-client-java" % "5.14.0", // Not latests, but it needs to match the server's version on testcontainers
  ))


  object Libraries {
    lazy val kafkaStreamsScala = "org.apache.kafka" %% "kafka-streams-scala" % Versions.kafkaVersion
//    lazy val kafka = "org.apache.kafka"      %% "kafka"               % Versions.kafkaVersion
    lazy val pureConfig         = "com.github.pureconfig"   %% "pureconfig"              % Versions.pureConfig
//    lazy val cats               = "org.typelevel"           %% "cats-core"               % Versions.cats
//    lazy val catsEffect         = "org.typelevel"           %% "cats-effect"             % Versions.catsEffect
    lazy val fs2                = "co.fs2"                  %% "fs2-core"                % Versions.fs2
    lazy val avro               = "org.apache.avro"         % "avro"                     % Versions.avro
    lazy val zio                = "dev.zio"                 %% "zio"                     % Versions.zio
    lazy val zioTest            = "dev.zio"                 %% "zio-test"                % Versions.zio
//    lazy val zioStreams         = "dev.zio"                 %% "zio-streams"             % Versions.zio
//    lazy val zioKafka           = "dev.zio"                 %% "zio-kafka"               % Versions.zioKafka
    lazy val alenzaFinantraKafkaServer = "com.avlino.alenza"       %% "alenza-finatra-kafka-streams-server"    % Versions.alenzakafkaserver
    lazy val circeGeneric       = "io.circe"                %% "circe-generic"           % Versions.circe
    lazy val kafkaAvro          = "io.confluent"            % "kafka-avro-serializer"    % Versions.kafkaAvro
    lazy val kafkaStreamsAvro   = "io.confluent"            % "kafka-streams-avro-serde" % Versions.kafkaAvro
    lazy val avro4sCore         = "com.sksamuel.avro4s"     % "avro4s-core_2.13"         % Versions.avro4s
    lazy val avro4sKafka        = "com.sksamuel.avro4s"     % "avro4s-kafka_2.13"        % Versions.avro4s
    lazy val smlTagging         = "com.softwaremill.common" %% "tagging"                 % Versions.smlCommon
    lazy val circeCore          = "io.circe"                %% "circe-core"              % Versions.circe
    lazy val kafkaStreamsCirce="com.goyeau" %% "kafka-streams-circe" % Versions.kafkaStreamsCirce
   lazy val zioStreams= "dev.zio" %% "zio-streams" % Versions.zio % Test
  }

}
