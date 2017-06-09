CREATE TABLE TwitterExample(
  tweetId BIGINT, username STRING,
  txt STRING, CreatedAt STRING,
  profileLocatin STRING,
  favc BIGINT, retweet STRING,
  retcount BIGINT, followerscount BIGINT)
comment 'This is twitter data'
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\t'
STORED AS TEXTFILE;



LOAD DATA INPATH /user/maria_dev/twitterdata1.txt' 
OVERWRITE INTO TABLE TwitterExample;
