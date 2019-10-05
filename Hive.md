# 大数据学习系列之Hive
### 常用命令
```java
// 创建Hive映射表,按照制表符分隔
create table wc(keyWord string, number int) row format delimited fields terminated by '\t'
//1.导入数据,input表示本地路径，没有input表示HDFS路径
load data local input '/data/wc.txt' into table wc;
//2.导入数据  
dfs -put /data/wc.txt /user/hive/warehouse/...
```