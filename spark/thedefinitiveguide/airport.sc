 val airportFile = spark.read.option("inferSchema","true").option("header","true").csv("hdfs:///user/spark/airport/airports.csv")
 
  airportFile.count()
  
  spark.conf.set("spark.sql.shuffle.partitions","50")
  
   val country_count = airportFile.groupBy("iso_country").count().sort(desc("count"))
   
   // shows top countries
   country_count.show()
   

// show regions in india

val india = airportFile.select("iso_region").filter(col("iso_country").like("IN"))
india.show()

