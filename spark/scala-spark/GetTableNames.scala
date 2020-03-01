import org.apache.spark.sql.SparkSession

object TableName extends SparkSessionTrait {

  def getTables(query: String): Seq[String] = {

    val logicalPlan = spark.sessionState.sqlParser.parsePlan(query)
    import org.apache.spark.sql.catalyst.analysis.UnresolvedRelation
    logicalPlan.collect { case r: UnresolvedRelation => r.tableName }
  }

  def main(args: Array[String]) {
    val query = "select * from table_1 as a left join table_2 as b on a.id=b.id join table_3 as c on c.id = b.id"
    for (elem <- getTables(query)) {
      println(elem)
      spark.catalog.refreshTable(elem)
    }
    println(query)
    println("==============")
    println(getTables(query))
  }
  
}
