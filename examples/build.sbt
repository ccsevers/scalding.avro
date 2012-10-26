import AssemblyKeys._ // put this at the top of the file

name := "examples"

organization := "scalding.avro.examples"

version := "0.1.0"

scalaVersion := "2.9.2"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"

libraryDependencies += "com.twitter" %% "scalding" % "0.8.0"

libraryDependencies += "scalding.avro" %% "avro-source" % "0.1.0"

assemblySettings


