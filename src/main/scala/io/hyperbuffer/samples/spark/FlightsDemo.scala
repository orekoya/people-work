package io.hyperbuffer.samples.spark
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SparkSession}

class FlightsDemo {

}

object FlightsDemo extends App {

  val spark = SparkSession
    .builder()
    .master("spark://localhost:7077")
    .appName("SparkFlightsDemo")
    .config("spark.sql.warehouse.dir", "warehouseLocation")
    .getOrCreate()

  spark.conf.set("spark.sql.shuffle.partitions", 5)
  spark.conf.set("spark.executor.memory", "2g")

  val range = spark.range(1000).toDF("number").count()

  println ("hello world")
}
