name := "avro-source"

organization := "scalding.avro"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"

resolvers += "Maven Central Repo" at "http://repo1.maven.org/maven2"

libraryDependencies += "com.twitter" %% "scalding" % "0.8.2"

libraryDependencies += "cascading.avro" % "avro-scheme" % "2.1.1" 



