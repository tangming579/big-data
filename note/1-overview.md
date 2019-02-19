# Hadoop概念

- **狭义Hadoop**：是一个适合大数据分布式存储（HDFS）、分布式计算（MapReduce）、资源调度（YARN）的平台

- **广义的Hadoop**：Hadoop整个生态系统。Hadoop生态系统是一个庞大的概念，Hadoop是其中最重要最基础的一部分；生态系统中的每个子系统只解决某一个特定的问题域，不搞统一型的全能系统，而是小而精的多个小系统。

Hadoop生态系统：

<div>
    <image src="img/hadoop.png"></image>
</div>
相关技术：

**文件存储：**Hadoop HDFS、Tachyon、KFS
**离线计算：**Hadoop MapReduce、Spark
**流式、实时计算：**Storm、Spark Streaming、S4、Heron
**K-V、NOSQL数据库：**HBase、Redis、MongoDB
**资源管理：**YARN、Mesos
**日志收集：**Flume、Scribe、Logstash、Kibana
**消息系统：**Kafka、StormMQ、ZeroMQ、RabbitMQ
**查询分析：**Hive、Impala、Pig、Presto、Phoenix、SparkSQL、Drill、Flink、Kylin、Druid
**分布式协调服务：**Zookeeper
**集群管理与监控：**Ambari、Ganglia、Nagios、Cloudera Manager
**数据挖掘、机器学习：**Mahout、Spark MLLib
**数据同步：**Sqoop
**任务调度：**Oozie

## HDFS

- HDFS：分布式文件存储系统（Hadoop Distributed File System）
- HDFS是GFS的克隆版
- HDFS特点：扩展性&容错性&海量数据存储

## YARN

- YARN：Hadoop资源管理器（Yet Another Resource Negotiator）

- 负责整个集群资源的管理和调度
- YARN特点：扩展性&容错性&多框Java

## MapReduce

- 分布式计算框架，源自Google的MapReduce论文
- Hadoop MapReduce是Google MapReduce的克隆版
- MapReduce特点：扩展性&容错性&海量数据离线处理

# Hadoop单节点安装

**软件要求**

1. Java环境
2. ssh

**安装软件**

```
sudo yum install ssh
sudo yum install rsync
```

下载Hadoop：[Apache Download Mirrors](http://www.apache.org/dyn/closer.cgi/hadoop/common/)

下载并安装Java