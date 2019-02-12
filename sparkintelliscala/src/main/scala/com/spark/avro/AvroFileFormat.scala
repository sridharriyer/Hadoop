package com.spark.avro

import org.apache.spark.sql.SparkSession
import java.nio.ByteBuffer

import com.spark.dataset.MutualFundDataset.spark
import org.apache.commons.lang3.SerializationUtils
import org.apache.spark.sql.types.StringType
object AvroFileFormat {

  def main(args : Array[String]): Unit = {

    val spark = SparkSession.builder
      .appName("MutualFund Analysis Avro Application")
      .master("local[*]")
      .getOrCreate()

    val avroDF = spark.read
      .format("com.databricks.spark.avro")
      .load("C:\\Users\\Sridhar\\Dev\\hadoop\\employee.avro")

    avroDF.printSchema()
    avroDF.show()
    //println(avroDF.count())
    //avroDF.withColumn("department", 'department.asInstanceOf[StringType]).select("department")

    import spark.sqlContext.implicits._
    avroDF.select("department").foreach {
     row => row.toSeq.foreach {
       col => col.asInstanceOf[StringType]
     }
   }
  }

}
