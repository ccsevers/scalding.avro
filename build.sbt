name := "avro-source"

organization := "scalding.avro"

version := "0.1.0"

scalaVersion := "2.9.2"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"

libraryDependencies += "com.twitter" %% "scalding" % "0.8.0"

libraryDependencies += "cascading.avro" % "avro-scheme" % "2.1.0"



