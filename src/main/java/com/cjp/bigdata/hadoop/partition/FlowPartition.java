package com.cjp.bigdata.hadoop.partition;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * mapper 操作之后，进去partition分区，分区数决定reduceTask
 */
public class FlowPartition extends HashPartitioner<Text, FlowBean> {

    @Override
    public int getPartition(Text key, FlowBean value, int numReduceTasks) {
        String phone = key.toString();
        String partition = phone.substring(0, 3);
        if ("137".equals(partition)) {
            return 0;
        }
        else if ("138".equals(partition)) {
            return 1;
        }
        else {
            return 2;
        }
    }
}
