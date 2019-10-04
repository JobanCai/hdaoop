package com.cjp.bigdata.hadoop.mysql;

import lombok.Data;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapred.lib.db.DBWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Data
public class ReceiveTable implements Writable, DBWritable {

    private String keyWord;
    private int number;

    public ReceiveTable(String keyWord, int number) {
        this.keyWord = keyWord;
        this.number = number;
    }


    public void write(DataOutput out) throws IOException {
        out.writeInt(this.number);
        Text.writeString(out, this.keyWord);
    }

    public void readFields(DataInput in) throws IOException {
        in.readInt();
        in.readUTF();
    }

    public void write(PreparedStatement statement) throws SQLException {
        statement.setInt(1, this.number);
        statement.setString(2, this.keyWord);
    }

    public void readFields(ResultSet resultSet) throws SQLException {
        this.number = resultSet.getInt(1);
        this.keyWord = resultSet.getString(2);
    }
}
