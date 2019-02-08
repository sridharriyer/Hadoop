 table = spark.createDataFrame([('Sri','US',35),('Swa','BGLR',36),('BIL','BGLR',38),('San','Pune',35),('Paw','US',35)],("Name","Country","Age"))
 table.rdd.getNumPartitions()
  tab = table.groupBy("Country").agg(count("Name"),count("Age")).sort("Country")
