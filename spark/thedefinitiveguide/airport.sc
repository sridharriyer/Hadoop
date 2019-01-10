 val airportFile = spark.read.option("inferSchema","true").option("header","true").csv("hdfs:///user/spark/airport/airports.csv")
 
  airportFile.count()
  
  spark.conf.set("spark.sql.shuffle.partitions","50")
  
   val country_count = airportFile.groupBy("iso_country").count().sort(desc("count"))
   
   
   country_count.show()
   
