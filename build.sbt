name := "people-work"
organization := "com.hyperbuffer.samples"
//version := "0.1"

resolvers += "bintray-spark-packages" at "https://dl.bintray.com/spark-packages/maven/"

resolvers += "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"

resolvers += "MavenRepository" at "https://mvnrepository.com/"

scalaVersion := "2.11.8"

val libs = Map("spark" -> "2.4.3", "csv" -> "1.5.0", "cassandra" -> "2.4.0")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % libs("spark") ,
  "com.databricks" %% "spark-csv" % libs("csv") ,
  "org.apache.spark" %% "spark-streaming" % libs("spark"),
  "org.apache.spark" % "spark-sql_2.11" % libs("spark"),
  "com.datastax.spark" %% "spark-cassandra-connector" % libs("cassandra")
)