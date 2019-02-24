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

**HDFS**

- HDFS：分布式文件存储系统（Hadoop Distributed File System）
- HDFS是GFS的克隆版
- HDFS特点：扩展性&容错性&海量数据存储

**YARN**

- YARN：Hadoop资源管理器（Yet Another Resource Negotiator）

- 负责整个集群资源的管理和调度
- YARN特点：扩展性&容错性&多框Java

**MapReduce**

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

ssh-keygen -t rsa
cp ~/.ssh/id_rsa.pub ~/.ssh/authorized_keys
```

**安装 java**

- 方法一：利用yum源来安装 jdk（此方法不需要配置环境变量，此方法只能安装openjdk）：

  1. 查看yum库中的java安装包 ：yum -y list java

  2. yum -y install java-1.8.0-openjdk*

     **注：**(安装完之后，默认的安装目录是在: /usr/lib/jvm/)

- 方法二：

  1. wget下载Hadoop的tar.gz包

  2. 解压缩包：tar -zxvf /home/ftp/jdk-8u111-linux-x64.tar.gz -C /home/soft

  3. 配置环境变量：vim /etc/profile

  4. ```
     export JAVA_HOME=/usr/local/java/jdk1.8.0_201
     export JRE_HOME=${JAVA_HOME}/jre
     export HADOOP_HOME=/usr/local/hadoop-3.2.0/hadoop-3.2.0/
     export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib
     export PATH=${JAVA_HOME}/bin:$HADOOP_HOME/bin:$PATH
     ```

     执行Profile：

     ```
     source /etc/profile
     ```


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

1. 安装Java JDK（同上）

2. 安装ssh（遇到提示直接回车）

   ```
   sudo yum -y install ssh
   ssh-keygen -t rsa
   cp ~/.ssh/id_rsa.pub ~/.ssh/authorized_keys	
   ```

3. 下载并解压hadoop

   ```
   下载：直接去cdh网站下载
   解压：tar -zxvf hadoop-3.2.0.tar.gz -C ~/app
   ```

4. hadoop配置文件的修改(hadoop_home/etc/hadoop)

   ```
   hadoop-env.sh
   		export JAVA_HOME=/home/hadoop/app/jdk1.7.0_79
   
   core-site.xml
   		<property>
   	        <name>fs.defaultFS</name>
   	        <value>hdfs://hadoop000:8020</value>
   	    </property>
   	    <property>
   	        <name>hadoop.tmp.dir</name>
   	        <value>/home/hadoop/app/tmp</value>
   	    </property>
   
   hdfs-site.xml
   		<property>
   	        <name>dfs.replication</name>
   	        <value>1</value>
   	    </property>
   ```

5. 启动hdfs

   - 格式化文件系统（仅第一次执行即可，不要重复执行）：

   ```
   cd /root/app/hadoop-3.2.0/bin
   ./hadoop namenode -format
   ```

   - 启动hdfs

   ```
    sbin/start-dfs.sh
   ```

   **注：**假如HDFS格式化后启动dfs出现以下错误：Attempting to operate on hdfs namenode as root



   验证是否启动成功：

6. 停止hdfs

**全分布式模式**

# Master-Slave Mode

## 概念

`主从设备模式`也叫做`主仆模式`英文简称为`Master-Slave`,核心思想是基于分而治之的思想,将一个原始任务分解为若干个语义等同的子任务,并由专门的工作者线程来并行执行这些任务,原始任务的结果是通过整合各个子任务的处理结果形成的.主要的使用场景有

- 并行计算,以提升计算性能
- 容错处理,以提升计算的可靠性
- 计算精度,以提高计算的精确程度

## 并行计算下模式举例

在分布式的系统中,这个模式还是比较常用的,简单的说,`主从(Master-Slave)`与`进程-线程`的关系类似,`Master`只有一台机器作为`Master`,其他机器作为`Slave`,这些机器同时运行组成了`集群`.`Master`作为任务调度者,给多个`Slave`分配计算任务,当所有的`Slave`将任务完成之后,最后由`Master`汇集结果,这个其实也是`MapReduce`思想所在.

例如在`Hadoop`中,`HDFS`采用了基于`Master/Slave`主从架构的分布式文件系统，一个`HDFS`集群包含一个单独的`Master`节点和多个`Slave`节点服务器，这里的一个单独的Master节点的含义是HDFS系统中只存在一个逻辑上的Master组件。一个逻辑的Master节点可以包括两台物理主机，即两台Master服务器、多台Slave服务器。一台Master服务器组成单`NameNode`集群，两台Master服务器组成双`NameNode`集群，并且同时被多个客户端访问，所有的这些机器通常都是普通的Linux机器，运行着用户级别(user-level)的服务进程.

<div>
    <image src="img/namenode-datanode.jpg"></image>
</div>



在上图中展示了 HDFS 的 NameNode , DataNode 以及客户端之间的存取访问关系, `NameNode` 作为 `Master` 服务，它负责管理文件系统的命名空间和客户端对文件的访问。`NameNode`会保存文件系统的具体信息，包括文件信息、文件被分割成具体`block`块的信息、以及每一个`block`块归属的`DataNode`的信息。对于整个集群来说，`HDFS`通过`NameNode`对用户提供了一个单一的命名空间。`DataNode`作为`slave`服务，在集群中可以存在多个。通常每一个`DataNode`都对应于一个物理节点。`DataNode`负责管理节点上它们拥有的存储，它将存储划分为多个`block`块，管理`block`块信息，同时周期性的将其所有的`block`块信息发送给`NameNode`。

## 优缺点

- 优点:准确性——将服务的执行委托给不同的从设备，具有不同的实现。
- 缺点:从设备是孤立的,没有共享的状态。主-从通信中的延迟可能是一个问题，例如在实时系统中。这种模式只能应用于可以分解的问题。