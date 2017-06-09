# WordCount in Spark


### Instructions to execute

* Create jar file to execute the wordcount program.

> sbt package

* Copy the HadoopFile.txt from src/main/resources folder to HDFS

> hdfs dfs -copyFromLocal /home/siyer/spark/WordCount/src/main/resources/HadoopFile.txt /user/siyer/files

* Execute the following command

> spark-submit --class "WordCount" --master local[*] target/scala-2.10/word-count_2.10-1.0.0.jar /user/siyer/files/HadoopFile.txt /user/siyer/files/
