import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSClient {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        //在本地的项目目录文件下创建文件
        fs.mkdirs(new Path("caijp"));
        fs.close();
        System.out.println("finish");
    }
}
