# HDFS

HDFS：分布式文件系统（Hadoop Distributed File System）

## HDFS架构

**数据块**

每个磁盘都有默认的数据块大小，这是磁盘进行数据读写的最小单位，磁盘块一般问512字节

HDFS中同样有块（block）的概念，但是大的多，默认为128 MB。HDFS上的文件被划分为块大小的多个分块（chunk），作为独立的存储单元

为什么HDFS中的块那么大？为了最小化寻址开销

**NameNode和DataNode**

HDFS集群有两类节点以管理节点-工作节点模式运行，即一个namenode（管理节点）和多个datanode（工作节点）

1个文件会被拆分成多个block

blocksize：128 M

130 M => 2个Block：128 M + 2 M

- NameNode：
  1. 负责客户端请求的相应
  2. 负责元数据的管理

- DataNode：
  1. 存储用户的文件对应的数据块（Block）
  2. 要定期向NameNode发送心跳信息，汇报本身及其所有Block的信息，健康状况

**缓存块**

通常datanode从磁盘读取块，但对于访问频繁的文件，其对应的块可能被显式的缓存在datanode的内存中，以堆外块缓存的形式存在

## Hadoop文件系统

Hadoop有一个抽象的文件系统概念，HDFS只是其中的一个实现

**数据副本**

<div>
    <image src="img/hdfsdatanodes.png"></image>
</div>

**副本放置策略**
数据是以块存储在datanode节点

- 第一个副本:假设提交文件的所在机器就是datanode节点，那么第一个块就存储在本节点上；
- 如果不是，就随机挑选一台磁盘不太慢的 cpu不太繁忙的节点上；
- 第二个副本:放置在于第一个副本的不同的机架的节点上第三个副本:与第二个副本相同的机架的不同的节点上