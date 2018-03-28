
name := "ExternalProject"
version := "0.1"
scalaVersion := "2.12.4"
organization in ThisBuild := "com.knoldus"
version in ThisBuild := "1.0-SNAPSHOT"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.0" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.4" % Test


lazy val `external-lagom` = (project in file("."))
  .aggregate(`external-lagom-api`, `external-lagom-impl`)

lazy val `external-lagom-api` = (project in file("external-lagom-api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `external-lagom-impl` = (project in file("external-lagom-impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest,
      "com.example" %% "hello-lagom-api" % "1.0-SNAPSHOT"
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`external-lagom-api`)
lazy val externalCall = lagomExternalScaladslProject("hello-lagom", "com.example" %% "hello-lagom-impl" % "1.0-SNAPSHOT")

lagomServiceGatewayPort in ThisBuild := 3333
lagomServiceLocatorPort in ThisBuild := 8010