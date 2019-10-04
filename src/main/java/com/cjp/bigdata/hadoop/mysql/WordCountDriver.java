package com.cjp.bigdata.hadoop.mysql;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class WordCountDriver {

    public static String driverClass = "com.mysql.jdbc.Driver";
    public static String dbUrl = "jdbc:mysql://111.230.xx.xxx:xxxx/hadoop";
    public static String userName = "root";
    public static String passwd = "xxxx";
    public static String inputFilePath = "C:\\Users\\CJP\\Desktop\\wc.txt";
    public static String tableName = "keyWord";
    public static String[] fields = {"total", "word"};

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf = new Configuration();
        DBConfiguration.configureDB(conf, driverClass, dbUrl, userName, passwd);
        Job job = Job.getInstance(conf);

        job.setJarByClass(WordCountDriver.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setMapOutputKeyClass(Text.class);

        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        job.setJobName("WordCount2MySQL");

        FileInputFormat.setInputPaths(job, new Path(inputFilePath));
        DBOutputFormat.setOutput(job, tableName, fields);
        job.waitForCompletion(true);
    }

}
