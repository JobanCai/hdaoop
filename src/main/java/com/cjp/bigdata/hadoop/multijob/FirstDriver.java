package com.cjp.bigdata.hadoop.multijob;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FirstDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
//        args[0] = "C:\\Users\\CJP\\Desktop\\multi";
//        args[1] = "C:\\Users\\CJP\\Desktop\\first";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        job.setJarByClass(FirstDriver.class);
        job.setMapperClass(FirstJobMapper.class);
        job.setReducerClass(FirstReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        FileInputFormat.setInputPaths(job, new Path("C:\\Users\\CJP\\Desktop\\multi"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\CJP\\Desktop\\first"));

        job.waitForCompletion(true);
    }

}
