name := "people-work"

version := "0.1"

scalaVersion := "2.11"

val sparkVersion = "2.4.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion //,
//  "org.apache.spark" %% "spark-csv" % sparkVersion,
//  "org.apache.spark" %% "spark-streaming" % sparkVersion,
//  "org.apache.spark" %% "spark-sql" % sparkVersion //,
//  "com.datastax.spark" %% "spark-cassandra-connector" % "2.4.0"
)