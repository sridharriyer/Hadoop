val flightData2015 = spark
                     .read
                     .option("inferSchema", "true")
                     .option("header","true")
                     .csv("/data/flight-data/csv/2015-summary.csv")


flightData2015.take(3)

flightData2015.sort("count").explain()
