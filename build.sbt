name := """parktripplanner"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

libraryDependencies += guice

// Database
libraryDependencies += javaJpa
libraryDependencies += javaJdbc
libraryDependencies += "org.hibernate" % "hibernate-core" % "5.2.14.Final"
libraryDependencies += "org.mariadb.jdbc" % "mariadb-java-client" % "2.2.2"

// GUI
libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.6.3",
  "org.webjars" % "bootstrap" % "3.3.7-1" exclude("org.webjars", "jquery"),
  "org.webjars" % "jquery" % "3.2.1"
)

libraryDependencies += "org.webjars" % "chartjs" % "2.7.0"
libraryDependencies += "com.google.maps" % "google-maps-services" % "0.2.6"


// Testing libraries for dealing with CompletionStage...
libraryDependencies += "org.assertj" % "assertj-core" % "3.6.2" % Test
libraryDependencies += "org.awaitility" % "awaitility" % "2.0.0" % Test


// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))


