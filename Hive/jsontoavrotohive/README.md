> java -jar avro-tools-1.8.2.jar fromjson --codec snappy --schema-file employee.avsc employee.json > employee.snappy.avro


## Hive Create table Query

```
CREATE EXTERNAL TABLE employees
  ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.avro.AvroSerDe'
  STORED as INPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerInputFormat'
  OUTPUTFORMAT 'org.apache.hadoop.hive.ql.io.avro.AvroContainerOutputFormat'
  LOCATION '/user/hive/avro/data/'
  TBLPROPERTIES ('avro.schema.url'='hdfs://sandbox-hdp.hortonworks.com:8020/user/hive/avro/schema/employee.avsc');
