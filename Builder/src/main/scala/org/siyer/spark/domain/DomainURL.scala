import java.io.{FileNotFoundException, IOException}

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object DomainUrl {

  def main(args: Array[String]) {

    val spark = SparkSession
      .builder()
      .appName("Domain and URL")
      .master("local[*]")
      .getOrCreate()

    try {

      val filePath = args(0)

      println(filePath)

      val df = spark.read.option("header", "true").option("delimiter", ",").csv(filePath)

      df.show()
      df.printSchema()
      df.createOrReplaceTempView("domain_url")

      val duDF = spark.sql("""SELECT * FROM domain_url""")

      val customerDF = spark.sql("""SELECT * FROM domain_url""")


      val domainUrlDF = spark.sql("""select a.domain, count(a.url) as url_count from (select distinct * from domain_url)a group by a.domain""")

      domainUrlDF.show()

      duDF.show()


      val dupDF = customerDF.groupBy("domain")
              .agg(count("url").alias("url_count"))

      dupDF.show()

      val dedup = customerDF.dropDuplicates()

      dedup.show()

      val uniqCount = dedup.groupBy("domain").agg(count("url")).alias("URL_COUNT")

      uniqCount.show()

    } catch {
      case e: ArrayIndexOutOfBoundsException => println(" Provide filepath as argument")
      case e: FileNotFoundException => println("Couldn't find that file.")
      case e: IOException => println("Had an IOException trying to read that file")
    } finally {
      spark.close()
    }
  }
}

