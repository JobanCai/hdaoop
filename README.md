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

**计算切片大小公式**
切片是逻辑切分，与block不同，block是物理切分。切片默认等于block大小，针对每个文件单独切片。一个切片对应一个MapTask.
默认是TextInputFormat切片机制，默认不管文件多小，都会是一个单独的切片
**1.1倍**
```java
Math.max(minSize, Math.min(maxSize, blockSize));
```
如果小文件太多，可以使用CombineTextInputFormat切片机制


### Yarn架构
#### ResourceManager
> 1.处理客户端请求2.监控NodeManager3.启动或监控ApplicationMaster4.资源的分配与调度
#### NodeManager
> 1.管理单个节点上的资源2.处理来自ResourceManager的命令3.处理来自ApplicationMaster的命令
#### ApplicationMaster
> 1.负责数据的切分2.为应用程序生气资源并分配给内部任务3.任务的监控与容错
#### Container
> Container是YARN中资源的抽象，他封装了某个节点上多维度资源，如内存、CPU、磁盘、网络等

### 工作机制
```
1.MapReduce提交程序到客户端所在节点
2.YarnRunner向ResourceManager申请Application
3.ResourceManager将该应用程序的资源路径返回给YarnRunner
4.该程序将运行所需资源提交到HDFS上
5.程序资源提交完成后，申请运行mrAppMaster
6.ResourceManager将用户的请求初始化成一个Task
7.其中一个NodeManager领取Task任务
8.该NodeManager创建container，并产生MRAppMaster
9.Container从HDFS上拷贝资源到本地
10.MrAppMaster向ResourceManager申请MapTask资源
11.Resource将运行MapTask任务分配给另外两个NodeManager，另外两个NodeManager分别领取任务并创建container
12.MapReduce向两个接收到任务的NodeManager发送程序启动脚本，这两个NodeManager分别启动MapTask，对MapTask对数据进行分区排序
13.MrAppMaster等待所有的MapTask运行完毕后，向ResourceManager申请容器，运行ReduceTask
14.ReduceTask向MapTask获取相应分区的数据
15.程序运行完毕后，MapReduce会向ResourceManager申请注销自己
```

### 作业提交全过程
```
作业提交全过程详解
（1）作业提交
第1步：Client调用job.waitForCompletion方法，向整个集群提交MapReduce作业。
第2步：Client向RM申请一个作业id。
第3步：RM给Client返回该job资源的提交路径和作业id。
第4步：Client提交jar包、切片信息和配置文件到指定的资源提交路径。
第5步：Client提交完资源后，向RM申请运行MrAppMaster。
（2）作业初始化
第6步：当RM收到Client的请求后，将该job添加到容量调度器中。
第7步：某一个空闲的NM领取到该Job。
第8步：该NM创建Container，并产生MRAppmaster。
第9步：下载Client提交的资源到本地。
（3）任务分配
第10步：MrAppMaster向RM申请运行多个MapTask任务资源。
第11步：RM将运行MapTask任务分配给另外两个NodeManager，另两个NodeManager分别领取任务并创建容器。
（4）任务运行
第12步：MR向两个接收到任务的NodeManager发送程序启动脚本，这两个NodeManager分别启动MapTask，MapTask对数据分区排序。
第13步：MrAppMaster等待所有MapTask运行完毕后，向RM申请容器，运行ReduceTask。
第14步：ReduceTask向MapTask获取相应分区的数据。
第15步：程序运行完毕后，MR会向RM申请注销自己。
（5）进度和状态更新
YARN中的任务将其进度和状态(包括counter)返回给应用管理器, 客户端每秒(通过mapreduce.client.progressmonitor.pollinterval设置)向应用管理器请求进度更新, 展示给用户。
（6）作业完成
除了向应用管理器请求作业进度外, 客户端每5秒都会通过调用waitForCompletion()来检查作业是否完成。时间间隔可以通过mapreduce.client.completion.pollinterval来设置。作业完成之后, 应用管理器和Container会清理工作状态。作业的信息会被作业历史服务器存储以备之后用户核查。
```
