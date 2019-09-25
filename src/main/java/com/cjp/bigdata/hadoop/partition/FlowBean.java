package com.cjp.bigdata.hadoop.partition;

import lombok.Data;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class FlowBean implements Writable {

    private long upFlow;

    private long downFlow;

    private long sumFlow;

    public FlowBean() {
    }

    public FlowBean(long downFlow, long upFlow) {
        this.downFlow = downFlow;
        this.upFlow = upFlow;
    }

    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(downFlow);
        out.writeLong(sumFlow);
    }

    public void readFields(DataInput in) throws IOException {
        this.upFlow = in.readLong();
        this.downFlow = in.readLong();
        this.sumFlow = in.readLong();
    }

    public void build(long downFlow, long upFlow) {
        this.downFlow = downFlow;
        this.upFlow = upFlow;
        this.sumFlow = upFlow + downFlow;
    }

    @Override
    public String toString() {
        return upFlow +
                "\t" + downFlow +
                "\t" + sumFlow;
    }
}
