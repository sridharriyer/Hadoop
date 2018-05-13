// Run this to start the spark-shell in scala

spark-shell  --packages com.databricks:spark-avro_2.10:2.0.1 org.apache.spark:spark-core_2.11:2.1.1 org.apache.spark:spark-sql_2.11:2.1.1


// Execute the below code

import com.databricks.spark.avro._
import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.types._
import org.apache.spark.sql


// This converts all the bigint columns to double
// You can use this to convert from anytype to any another type
def castAllTypedColumnsTo(df: DataFrame, sourceType: LongType, targetType: DoubleType) = {
df.schema.filter(_.dataType == sourceType).foldLeft(df) {
    case (acc, col) => acc.withColumn(col.name, df(col.name).cast(targetType))
 }
}

val df = sqlContext.read.format("com.databricks.spark.avro").load("file:///home/siyer/mtg_flat_bal_fact_moend_007.avro")
df.show()

val mydf = castAllTypedColumnsTo(df,LongType,DoubleType)
mydf.show()




