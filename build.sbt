enablePlugins(GatlingPlugin, AssemblyPlugin)

name := "gatling-starter"

version := "0.1"

scalaVersion := "2.11.12"

parallelExecution in Test := false

scalacOptions := Seq(
  "-encoding", "UTF-8", "-target:jvm-1.8", "-deprecation",
  "-feature", "-unchecked", "-language:implicitConversions", "-language:postfixOps")

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.1" % "test,it"
libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.1" % "test,it"
libraryDependencies += "com.typesafe"          % "config"                    % "1.3.3" % "test,it"
libraryDependencies += "net.ceedubs"           % "ficus_2.11"                % "1.1.2" % "test,it"
libraryDependencies += "org.rogach"            % "scallop_2.11"              % "3.1.5" % "test,it"

Project.inConfig(IntegrationTest)(baseAssemblySettings)
assemblyJarName in (IntegrationTest, assembly) := s"${name.value}-${version.value}.jar"
mainClass in (IntegrationTest, assembly) := Some("io.gatling.app.Gatling")
assemblyMergeStrategy in (IntegrationTest, assembly) := {
  case x if x.contains("io.netty.versions.properties")  => MergeStrategy.rename
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
