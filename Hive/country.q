CREATE TABLE country_list (name STRING);
DESCRIBE EXTENDED country_list;
CREATE EXTERNAL TABLE country_list (name STRING) LOCATION '/user/maria_dev/country_list';