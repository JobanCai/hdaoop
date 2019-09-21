package com.cjp.bigdata.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 统计单词的Mapper类
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    Text text = new Text();
    IntWritable num = new IntWritable(1);

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] words = line.split(" ");
        for (String word : words) {
            text.set(word);
            context.write(text, num);
        }
    }
}
