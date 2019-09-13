package io.hyperbuffer.samples.spark

import org.apache.spark.sql.SparkSession

class FlightsDemo {

}

object FlightsDemo {

  private val baseDir = "/home/viktor/projects/samples/spark-demo/data/"
  private val fileName = "/opt/spark-data/2015-summary.csv"

  private val spark = SparkSession
    .builder()
    //    .master("spark://spark-master:7077")
    .master("local")
    .appName("SparkFlightsDemo")
    .config("spark.cores.max", "1")
    .config("spark.executor.memory", "512m")
    //    .config("spark.eventLog.enabled", "false")
    //    .config("spark.dynamicAllocation.enabled", "true")
    //    .config("spark.dynamicAllocation.minExecutors", "12")
    //    .config("spark.dynamicAllocation.executorIdleTimeout", "600")
    //    .config("spark.shuffle.service.enabled", "true")
    .config("spark.executor.extraJavaOptions", "-Ddm.logging.level=INFO")
    .getOrCreate()

  def main(args: Array[String]): Unit = {
    println("**** hello world *****")
    spark.catalog.listDatabases().show()
    loadCsv()
    println("************* job done ***********************")
    spark.stop()
  }

  def loadCsv(): Unit = {
    val flights = spark.read.option("inferSchema", "true")
      .option("header", "true")
      //      .csv(fileName)
      .csv(s"$baseDir/flight-data/csv/2015-summary.csv")
      .toDF()

    flights.createOrReplaceTempView("flights_table")
    val sql = spark.sql(
      """
        |SELECT DEST_COUNTRY_NAME, sum(count) as destination_total
        |FROM flights_table
        |GROUP BY DEST_COUNTRY_NAME
        |ORDER BY sum(count) DESC
        |LIMIT 5
        |""".stripMargin)

    sql.show()
    //    println(flights.show())
  }
}
