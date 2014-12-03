lazy val root = (project in file(".")).
settings(
  name := "jdk8-tour-in-scala",
  version := "1.0",
  scalaVersion := "2.11.4"
  
)

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"
