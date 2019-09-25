package com.cjp.bigdata.hadoop.partition;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    Text k = new Text();
    FlowBean flowBean = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

//        19 	13975057813	192.168.100.16	www.baidu.com	11058	48243	200
//        20 	13768778790	192.168.100.17			120	120	200
//        21 	13568436656	192.168.100.18	www.alibaba.com	2481	24681	200
//        22 	13568436656	192.168.100.19			1116	954	200

        String line = value.toString();
        String[] words = line.split("\t");
        String phone = words[1];
        Long upFlow = Long.valueOf(words[words.length - 3]);
        Long downFlow = Long.valueOf(words[words.length - 2]);
        k.set(phone);
        flowBean.build(downFlow, upFlow);
        context.write(k, flowBean);
    }
}
