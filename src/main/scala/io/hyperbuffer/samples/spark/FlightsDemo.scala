package io.hyperbuffer.samples.spark

import org.apache.spark.sql.SparkSession

class FlightsDemo {

}

object FlightsDemo extends App {

  //  import spark.implicits._

  val spark = SparkSession
    .builder()
    .master("spark://localhost:7077")
    .appName("SparkFlightsDemo")
    .config("spark.cores.max", "1")
    .getOrCreate()

  spark.conf.set("spark.sql.shuffle.partitions", 5)
//  spark.conf.set("spark.executor.memory", "1g")

  val range = spark.range(0L, 10L).toDF("number")

  println("**** hello world *****")

  range.show()
  println(spark.catalog.listDatabases().show(false))

  //  spark.sparkContext.stop()
  spark.stop()
  //  println(range)
}
