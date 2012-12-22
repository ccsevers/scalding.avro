import AssemblyKeys._ // put this at the top of the file

assemblySettings

name := "examples"

organization := "scalding.avro.examples"

version := "0.1.0"

scalaVersion := "2.9.2"

resolvers += "Radlab Repository" at "http://scads.knowsql.org/nexus/content/groups/public/"

resolvers += "Concurrent Maven Repo" at "http://conjars.org/repo"

libraryDependencies += "com.twitter" %% "scalding" % "0.8.0"

libraryDependencies += "scalding.avro" %% "avro-source" % "0.1.0"

libraryDependencies += "edu.berkeley.cs" %% "avro-plugin" % "2.1.4-SNAPSHOT"

scalacOptions <++= update map { report =>
  val pluginClasspath = report matching configurationFilter(Configurations.CompilerPlugin.name)
  pluginClasspath.map("-Xplugin:" + _.getAbsolutePath).toSeq
}

addCompilerPlugin("edu.berkeley.cs" %% "avro-plugin" % "2.1.4-SNAPSHOT" % "plugin")

