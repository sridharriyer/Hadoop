>>> people = [
...   (10, "blue"),
...   (13, "red"),
...   (15, "blue"),
...   (99, "red"),
...   (67, "blue")
... ]
>>> df = people.createDataFrame()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'list' object has no attribute 'createDataFrame'
>>> df = people.toDF()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'list' object has no attribute 'toDF'
>>> df = people.toDF("age","color")
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'list' object has no attribute 'toDF'
>>> df = spark.createDataFrame(people,("age","color"))
>>> df.rdd.getNumPartition()
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'RDD' object has no attribute 'getNumPartition'
>>> df.rdd.getNumPartitions()
4
>>> df.repartition($"color")
  File "<stdin>", line 1
    df.repartition($"color")
                   ^
SyntaxError: invalid syntax
>>> df.repartition("color")
DataFrame[age: bigint, color: string]
>>> df.rdd.getNumPartitions()
4
>>> df.write.csv("/user/spark/age_color")
>>> df.rdd.getNumPartitions()
4
>>> people_df = df.write.csv("/user/spark/age_color")
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/usr/hdp/current/spark2-client/python/pyspark/sql/readwriter.py", line 885, in csv
    self._jwrite.csv(path)
  File "/usr/hdp/current/spark2-client/python/lib/py4j-0.10.6-src.zip/py4j/java_gateway.py", line 1160, in __call__
  File "/usr/hdp/current/spark2-client/python/pyspark/sql/utils.py", line 69, in deco
    raise AnalysisException(s.split(': ', 1)[1], stackTrace)
pyspark.sql.utils.AnalysisException: u'path hdfs://sandbox-hdp.hortonworks.com:8020/user/spark/age_color already exists.;'
>>> people_df = df.write.csv("/user/spark/age_color")
>>> people_df.repartition("color")
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
AttributeError: 'NoneType' object has no attribute 'repartition'
>>> df.repartition("color")
DataFrame[age: bigint, color: string]
>>> df.show()
+---+-----+
|age|color|
+---+-----+
| 10| blue|
| 13|  red|
| 15| blue|
| 99|  red|
| 67| blue|
+---+-----+

>>> df.repartition(10)
DataFrame[age: bigint, color: string]
>>> df = df.repartition("color")
>>> df = df.repartition(2)
>>> colorDf = df.repartition($"color")
  File "<stdin>", line 1
    colorDf = df.repartition($"color")
                             ^
SyntaxError: invalid syntax
>>> colorDf = df.repartition(df("color"))
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
TypeError: 'DataFrame' object is not callable
>>> df.sample(true,1)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
NameError: name 'true' is not defined
>>> df.sample(1,1)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/usr/hdp/current/spark2-client/python/pyspark/sql/dataframe.py", line 731, in sample
    "got [%s]." % ", ".join(argtypes))
TypeError: withReplacement (optional), fraction (required) and seed (optional) should be a bool, float and number; however, got [<type 'int'>, <type 'int'>].
>>> df.sample(True,1)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
  File "/usr/hdp/current/spark2-client/python/pyspark/sql/dataframe.py", line 731, in sample
    "got [%s]." % ", ".join(argtypes))
TypeError: withReplacement (optional), fraction (required) and seed (optional) should be a bool, float and number; however, got [<type 'bool'>, <type 'int'>].
>>> df.sample(True,0.01)
DataFrame[age: bigint, color: string]
>>> sample_df = df.sample(True,0.01)
>>> sample_df.show()
+---+-----+
|age|color|
+---+-----+
+---+-----+

>>> sample_df = df.sample(True,0.1)
>>> sample_df.show()
+---+-----+
|age|color|
+---+-----+
| 67| blue|
+---+-----+

>>> sample_df.rdd.getNumPartitions()
2
>>> sample_df.repartition(1)
DataFrame[age: bigint, color: string]
>>> sample_df.rdd.getNumPartitions()
2
>>> sample_write = sample_df.repartition(1)
>>> sample_write.write.csv("/user/spark/age_color/repart")
>>> sample_df.rdd.getNumPartitions()
2
>>> sample_write.rdd.getNumPartitions()
1
>>> part_col_df = df.repartition("color")
>>> part_col_df.write.csv("/user/spark/age_color/repart_color")
>>> part_col_df = df.repartition(2)
>>> part_col_df.write.csv("/user/spark/age_color/repart_color/2")
>>>
