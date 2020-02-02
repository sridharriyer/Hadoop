package org.siyer.spark.builder

import java.io.{FileNotFoundException, IOException}

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object Customer {

  def main(args: Array[String]) {

    val spark = SparkSession
      .builder()
      .appName("Customer")
      .master("local[*]")
      .getOrCreate()

    try {

      val filePath = args(0)

      println(filePath)

      val df = spark.read.option("header", "true").option("delimiter", ",").csv(filePath)

      df.show()
      df.printSchema()
      df.createOrReplaceTempView("customer")

      val customerDF = spark.sql("""SELECT * FROM customer""")

      val dupDF = customerDF.groupBy("cid", "cname", "cemail")
        .agg(count("cid").alias("countID"))
        .filter(column("countID") > 1)

      dupDF.show()

    } catch {
      case e: ArrayIndexOutOfBoundsException => println(" Provide filepath as argument")
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Had an IOException trying to read that file")
    } finally {
      spark.close()
    }
  }
}

