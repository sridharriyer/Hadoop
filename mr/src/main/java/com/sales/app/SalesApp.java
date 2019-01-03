package com.sales.app;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;

public class SalesApp {

    public static void main(String[] args) {

        JobClient client = new JobClient();
        JobConf jobConf = new JobConf(SalesApp.class);

        jobConf.setJobName("Sales Per Country");
        jobConf.setOutputKeyClass(Text.class);
        jobConf.setOutputValueClass(IntWritable.class);

        jobConf.setMapperClass(com.sales.mapper.SalesMapper.class);
        jobConf.setReducerClass(com.sales.reducer.SalesReducer.class);

        jobConf.setInputFormat(TextInputFormat.class);
        jobConf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(jobConf,new Path(args[0]));
        FileOutputFormat.setOutputPath(jobConf,new Path(args[1]));

        client.setConf(jobConf);

        try {
            JobClient.runJob(jobConf);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
