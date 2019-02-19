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

**安装 java**

利用yum源来安装 jdk（此方法不需要配置环境变量）：

1. 查看yum库中的java安装包 ：yum -y list java*
2. yum -y install java-1.8.0-openjdk*

(安装完之后，默认的安装目录是在: /usr/lib/jvm/java-1.8.0-openjdk-1.8.0.151-1.b12.el7_4.x86_64)

**安装 Hadoop**

下载安装Hadoop：[Apache Download Mirrors](http://www.apache.org/dyn/closer.cgi/hadoop/common/)

执行命令：（此命令会展示hadoop脚本文档）

```
bin/hadoop
```

现在，可以使用以下三种模式运行hadoop集群：

- 本地模式 Local (Standalone) Mode
- 伪分布式模式 Pseudo-Distributed Mode
- 全分布式模式 Fully-Distributed Mode

**本地模式**

默认情况下，Hadoop配置为非分布式模式运行的，作为一个单独的java进程，这种模式下对调试是非常有用的

下面的示例复制未打包的conf目录作为输入，然后查找并显示给定正则表达式的每个匹配项。输出被写入给定的输出目录。

```
$ mkdir input
$ cp etc/hadoop/*.xml input
$ bin/hadoop jar share/hadoop/mapreduce/hadoop-mapreduce-examples-2.9.2.jar grep input output 'dfs[a-z.]+'
$ cat output/*
```

**伪分布式模式**



**全分布式模式**

