 pyspark --packages com.databricks:spark-avro_2.10:2.0.1,org.apache.spark:spark-core_2.11:2.1.1,org.apache.spark:spark-sql_2.11:2.1.1"


from pyspark.sql.types import DoubleType

df = sqlContext.read.format("com.databricks.spark.avro").load("file:///home/siyer/mtg_flat_bal_fact_moend_007.avro")
df.show()

my_dict = dict(df.dtypes)

def int_to_double(my_dict, df):
    for k in my_dict:
        if my_dict.get(k) == 'bigint':
            df = df.withColumn(k, df[k].cast(DoubleType()))
    return df


my_df = int_to_double(my_dict,df)
my_df.show()
