import Dependencies.{Libraries, testAndITTestDependencies}
ThisBuild / version := "0.1.0-SNAPSHOT"

//ThisBuild / scalaVersion := "2.13.12"
lazy val commonSettings = Seq(
  scalaVersion := "2.13.12",
  resolvers += ("Confluent Maven Repo" at "http://packages.confluent.io/maven/").withAllowInsecureProtocol(true)
  ,libraryDependencies++=libraries
)
resolvers in ThisBuild+= ("Confluent Maven Repo" at "http://packages.confluent.io/maven/").withAllowInsecureProtocol(true)
scalaVersion := "2.13.12"
val libraries= Seq(
  Libraries.kafkaStreamsScala,
  Libraries.pureConfig,
//  Libraries.cats,
  Libraries.fs2,
//  Libraries.catsEffect,
//  Libraries.zioKafka,
//  Libraries.zioKafka % Test,
  Libraries.zio,
  Libraries.zioTest,
  Libraries.alenzaFinantraKafkaServer,
  Libraries.circeGeneric,
  Libraries.kafkaAvro,
  Libraries.kafkaStreamsAvro,
  Libraries.avro4sCore,
  Libraries.avro4sKafka,
  Libraries.smlTagging,
  Libraries.circeCore,
  Libraries.kafkaStreamsCirce,
)++testAndITTestDependencies


lazy val domain   = project.settings(commonSettings)
lazy val navisImpl: Project = project.settings(commonSettings).dependsOn(domain)
lazy val opusImpl: Project = project.settings(commonSettings).dependsOn(domain)
lazy val platform=  project.settings(commonSettings).dependsOn(navisImpl,opusImpl)
//lazy val registry =project.settings(commonSettings).dependsOn(navisImpl,opusImpl)
