import sbt._
import Defaults._

//resolvers += Resolver.url("sbt-plugin-releases",
//  new URL("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(
//  Resolver.ivyStylePatterns)

resolvers += "bintray-sbt-plugins" at "https://dl.bintray.com/sbt/sbt-plugin-releases/"


//libraryDependencies += sbtPluginExtra(
//  m = "com.github.saurfang" % "sbt-spark-submit" % "0.0.4", // Plugin module name and version
//  sbtV = "0.13",    // SBT version
//  scalaV = "2.10"    // Scala version compiled the plugin
//)
//addSbtPlugin("com.github.saurfang" % "sbt-spark-submit_scala_2.10" % "0.0.4")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.7.2")