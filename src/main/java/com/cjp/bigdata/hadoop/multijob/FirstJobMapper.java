package com.cjp.bigdata.hadoop.multijob;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * first job mapper
 */
public class FirstJobMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    //    aa abc
    //    bbb aa

    String name = "";
    IntWritable v = new IntWritable(1);
    Text k = new Text();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //获取切片信息，此处已经明确知道是按单个文件进行切片，因为每个文件都不会大于32M
        FileSplit split = (FileSplit) context.getInputSplit();
        name = split.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split(" ");
        for (String field : fields) {
            k.set(field + "--" + name);
            context.write(k, v);
        }
    }
}
