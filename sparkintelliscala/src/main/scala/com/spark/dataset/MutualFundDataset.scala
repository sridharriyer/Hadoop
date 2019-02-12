package com.spark.dataset

import org.apache.spark.sql.SparkSession

case class EmployeeData(empno: Integer, ename: String, designation: String, manager: String, hire_date: String, sal: Integer, deptno: Integer)

object MutualFundDataset extends App {

  val spark = SparkSession.builder
    .appName("MutualFund Analysis Application")
    .master("local[*]")
    .getOrCreate()

  import spark.sqlContext.implicits._


  val empDataSet = spark.read
    .option("inferSchema", "true")
    .option("header", "true")
    .csv("emp_data.csv").as[EmployeeData]

  val empDF = empDataSet.toDF()

  val multiplePartitions = empDF.repartition($"deptno")

  println("Number of Partitons: " + multiplePartitions.rdd.getNumPartitions)

  multiplePartitions.write

  multiplePartitions.count()

  val empWithHighSal = empDataSet.filter(empObj => empObj.sal > 1000 & empObj.designation == "CLERK")

  empWithHighSal.show

  val empGroupDS = empDataSet.groupBy("deptno").sum("sal").as("Department Salary")

  empGroupDS.show
}

/*
select sum(salary),deptno
from emp_data
group by deptno
*/