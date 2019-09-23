package io.hyperbuffer.samples.spark

import java.nio.file.{Files, Paths}

import org.apache.spark.SparkFiles
import org.apache.spark.sql.SparkSession

import scala.sys.process._

object FlightsDemo {

  private val spark = SparkSession
    .builder()
    //        .master("spark://spark-master:7077")
    .master("local")
    .appName("SparkFlightsDemo")
    .config("spark.cores.max", "4")
    .config("spark.executor.memory", "1g")
    //    .config("spark.dynamicAllocation.enabled", "true")
    //    .config("spark.dynamicAllocation.minExecutors", "12")
    //    .config("spark.dynamicAllocation.executorIdleTimeout", "600")
    //    .config("spark.shuffle.service.enabled", "true")
    .config("spark.executor.javaDriverOptions", "-Ddm.logging.level=DEBUG")
    .config("spark.executor.extraJavaOptions", "-Ddm.logging.level=DEBUG")
    .getOrCreate()

  def main(args: Array[String]): Unit = {
    //  override def main(args: Array[String]): Unit = {
    println("**** hello world *****")
    spark.catalog.listDatabases().show()
    loadCsv()
    println("************* job done ***********************")
    spark.stop()
  }

  //    GATEWAY_HOST=$(docker network inspect local_talq_network --format='{{range .IPAM.Config}}{{.Gateway}}{{end}}');

  def loadCsv(): Unit = {
    def filename = "2010-summary.csv"

    //    spark.sparkContext.addFile("http://10.20.6.1:28080/2010-summary.csv")
    spark.sparkContext.addFile(s"http://localhost:28080/$filename")
    // /sbin/ip route|awk '/default/ { print $3 }'

    //    spark.sparkContext.addFile(s"http://filer/$filename")

    println("+++++++++++++++++++++++++++")
    val f = Files.exists(Paths.get(SparkFiles.get(filename)))
    if (f) {
      println("file does exist")
    } else {
      println("sorry we couldn't actually find the file")
    }
    println(SparkFiles.getRootDirectory())
    s"ls -lath ${SparkFiles.getRootDirectory()}" !

    println("+++++++++++++++++++++++++++")
    val flights = spark.read.option("inferSchema", "true")
      .option("header", "true")
      .csv(SparkFiles.get(filename))
      //      .withColumn("random", lit(1))
      .toDF()
    import org.apache.spark.sql.functions._

    flights.filter(col("sum(count) > 5000"))

    flights.createOrReplaceTempView("flights_table")
    val sql = spark.sql(
      """
        |SELECT DEST_COUNTRY_NAME, sum(count) as destination_total, random
        |FROM flights_table
        |GROUP BY DEST_COUNTRY_NAME
        |ORDER BY sum(count) DESC
        |LIMIT 5
        |""".stripMargin)


    sql.show()

  }

}
