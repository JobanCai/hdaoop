# 大数据学习系列之Hadoop

### HDFS
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

### MapReduce学习
MapTask阶段和ReduceTask阶段3.Driver阶段,相当于Yarn集群客户端，用于提交整个程序到Yarn集群，提交的是封装了MapReduce程序相关运行参数的job对象
> 缺点:1.不擅长实时计算,2.不擅长流式计算,流式计算输入是动态的,而MapReduce输入数据集是静态的3.不擅长DAG计算（多个应用之间存在依赖关系,后一个应用程序的输入为前一个应用程序的输出.每个MapReduce作业的输出结果都会写入磁盘,会造成大量磁盘IO,会导致性能低下）



