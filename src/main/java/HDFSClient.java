import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HDFSClient {

    //------------------------框架封装好的API------------------------------------------------//

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
        //在客户端代码中设置参数值
//        configuration.set("dfs.replication", "2");
        //指定用户名连接hdfs
//        FileSystem fs = FileSystem.get(new URI("hdfs://127.0.0.1:9000"), configuration, "CJP");
        FileSystem fs = FileSystem.get(configuration);
        fs.copyFromLocalFile(new Path("C:\\oem8.log"), new Path("caijp"));
        fs.close();
        System.out.println("over");
    }
    /**
     * 参数优先级排序:1.在客户端代码中设置的参数之 -> 2.在classPath下用户自定义配置文件 -> 3.服务器中的默认配置文件
     */


    @Test
    public void copyToLocalFile() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        // userRawLocalFileSystem 是否开启文件校验
        fs.copyToLocalFile(false, new Path("caijp/oem8.log"), new Path("C:\\Users\\CJP\\Desktop\\oem8.log"), true);
        fs.close();
    }

    @Test
    public void testListFile() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        RemoteIterator<LocatedFileStatus> iterator = fs.listFiles(new Path("caijp"), true);
        while (iterator.hasNext()) {
            LocatedFileStatus fileStatus = iterator.next();
            System.out.println(fileStatus.getPath().getName());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getGroup());
            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation location : blockLocations) {
                String[] hosts = location.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }
        fs.close();
    }

    //------------------------IO流的方式实现文件上传下载------------------------------------------------//
    @Test
    public void testPutFileToHDFS() throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(configuration);
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\CJP\\Desktop\\caijp-upload.docx"));
        // Hadoop 提供的输出流
        FSDataOutputStream fos = fs.create(new Path("caijp/caijp-upload.docx"));
        // 调用此API会自动关闭输入输出流
        IOUtils.copyBytes(fis, fos, configuration);
        // 关闭资源
        fs.close();
    }



}
