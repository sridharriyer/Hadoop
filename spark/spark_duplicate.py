%python

from pyspark.sql.functions import count, column, desc

df = spark.sql("select * from dev.customer")

# show customer ids which have more than 6 duplicate records
d = df.groupBy("customerid").agg(count("customerid").alias("countid")).filter(column("countid") > 6).sort(desc('countid'))

d.show()

df.filter(column('customerid').like('%c2310e40%')).show()
