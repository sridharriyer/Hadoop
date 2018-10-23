val x = (1 to 10).toList
val numbersDf = x.toDF("number")
numbersDf.show()  // shows 1 to 10 numbers

numbersDf.rdd.partitions.size  // this prints 2 in my machine
numbersDf.write.csv("/user/spark/numbers") // writes the 2 partitions in the mentioned location
