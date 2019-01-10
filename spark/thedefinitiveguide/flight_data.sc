
// the file is not read gives exception
// java.net.ConnectException: Call From sandbox-hdp.hortonworks.com/172.18.0.2 to localhost:9000 failed on connection exception: java.net.ConnectException: Connection refused; For more details see:  http://wiki.apache.org/hadoop/ConnectionRefused
val flightData2015 = spark
                     .read
                     .option("inferSchema", "true")
                     .option("header","true")
                     .csv("/data/flight-data/csv/2015-summary.csv")


flightData2015.take(3)

flightData2015.sort("count").explain()

spark.conf.set("spark.sql.shuffle.partitions", "5")

flightData2015.sort("count").take(2)

// to sql

flightData2015.createOrReplaceTempView("flight_data_2015")


