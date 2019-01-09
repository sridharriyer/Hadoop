val flightData2015 = spark.read.option("inferSchema", "true").option("header","true").csv("/data/flight-data/csv/2015-summary.csv")
