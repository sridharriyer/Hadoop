val text = List("Hello this is a simple text file to count the number of repeated words in this file. Like aa and aa should be counted as 2")
val count = text.flatMap(line => line.split(" ")).map(word => (word,1)).reduceByKey(_+_)
count.collect()
