package com.cjp.bigdata.hadoop.multijob;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SecondMapper extends Mapper<LongWritable, Text, Text, Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
        String[] fields = line.split("--");
        k.set(fields[0]);
        String name = fields[1].split("\t")[0];
        String count = fields[1].split("\t")[1];
        v.set(name + "-->" + count);
        context.write(k, v);
    }
}
