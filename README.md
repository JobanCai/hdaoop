# 大数据学习系列之Hadoop

#### 启动hadoop集群
```
sbin/start-dfs.sh
sbin/start-yarn.sh
```
#### hadoop shell操作
```shell script
-moveFromLocal 从本地剪切粘贴到HDFS
-appendToFile 追加一个文件到已经存在的文件末尾
-copyFromLocal 从本地文件系统中拷贝文件到HDFS路径去
-copyToLocal 从HDFS拷贝到本地
-cp  从HDFS的一个路径拷贝到HDFS的另一个路径
-get 等同于copyToLocal,就是从HDFS下载文件到本地
-getmerge 合并下载多个文件,比如HDFS的目录 /user/hadoop/test下有多个文件:log.1, log.2,log.3,...
-put 等同于copyFromLocal
-setrep 设置HDFS中文件的副本数量
```