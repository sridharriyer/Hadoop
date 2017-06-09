# Command to find out the data size of hive tables. If needed in GB,
# divide by 1073741824
hdfs dfs -du /user/siyer/ | awk '{ print int($1/(1048576)) "MB\t" $2}'
