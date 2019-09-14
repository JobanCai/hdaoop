import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSClient {
    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        //在本地的项目目录文件下创建文件
        fs.mkdirs(new Path("caijp"));
        fs.close();
        System.out.println("finish");
    }

    @Test
    public void testCopyFromLocal() throws IOException, URISyntaxException, InterruptedException {
        Configuration configuration = new Configuration();
        //指定用户名连接hdfs
        //FileSystem fs = FileSystem.get(new URI("hdfs://127.0.0.1:9000"), configuration, "CJP");
        FileSystem fs = FileSystem.get(configuration);
        fs.copyFromLocalFile(new Path("C:\\oem8.log"), new Path("caijp"));
        fs.close();
        System.out.println("over");
    }
}
