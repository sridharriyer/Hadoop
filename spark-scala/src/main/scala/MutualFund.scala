import org.apache.spark.sql.SparkSession

object MutualFund {

  def main(args: Array[String]) {

    val employeeDF = Seq( (123,"sri",1), (345,"swa",3), (723,"har",2), (727,"krsh",2), (767,"kar",1), (907,"swe",3), (700,"sam",1), (777,"apr",2), (527,"jon",3), (232,"kol",2), (627,"kra",3), (703,"krsh",2), (729,"krsha",1), (717,"krish",1)).toDF("emp_id","name","dept_id")
    val departmentDF = Seq((1,"dev"), (2,"stu"), (3,"HR")).toDF("dept_id","dept_name")

    val emp = employeeDF.as("e")

    val dept = departmentDF.as("d")

    val emp_dept_df = emp.join(dept, col("e.dept_id") === col("d.dept_id"), "inner")

    emp_dept_df.select("*").show

  }
}