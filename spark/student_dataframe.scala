import org.apache.spark.sql.Row

case class Student(student_name: String, marks: List[Int])

val student = Seq(Row("Sridhar", List(10,30,40)), Row("Swathi", List(40,40,40)), Row("Hari", List(40,40,40)))

val rdd= sc.parallelize(student) 

val rows = rdd.map({ case Row(student_name:String,marks:List[Int]) => Student(student_name,marks)}).toDF()

val all_rows= rows.select(rows("student_name"), explode(rows("marks"))).show()

val avg_row = all_rows.select("student_name", avg("col")).groupby("student_name")

all_rows.groupBy("student_name").avg("col").show()
