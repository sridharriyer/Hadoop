import org.apache.spark.sql.SparkSession

trait SparkSessionTrait {

  lazy val spark: SparkSession = {
    SparkSession
      .builder()
      .master("local")
      .appName("TraitBuilder Unit Tests")
      .getOrCreate()
  }

}
