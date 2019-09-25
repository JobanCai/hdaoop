package com.cjp.bigdata.hadoop.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowReduce extends Reducer<Text, FlowBean, Text, FlowBean> {

    private FlowBean v = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {

        long upFlow = 0L;
        long downFlow = 0L;
        for (FlowBean flowBean : values) {
//            v.build(flowBean.getDownFlow(), flowBean.getUpFlow());
            upFlow += flowBean.getUpFlow();
            downFlow += flowBean.getDownFlow();
        }
        v.build(downFlow, upFlow);
        context.write(key, v);
    }
}
