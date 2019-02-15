# HDFS

HDFS：分布式文件系统（Hadoop Distributed File System）

## HDFS概念

**数据块**

每个磁盘都有默认的数据块大小，这是磁盘进行数据读写的最小单位，磁盘块一般问512字节

HDFS中同样有块的概念，但是大的多，默认为128 MB。HDFS上的文件被划分为块大小的多个分块（chunk），作为独立的存储单元

为什么HDFS中的块那么大？为了最小化寻址开销

**namenode和datanode**

HDFS集群有两类节点以管理节点-工作节点模式运行，即一个namenode（管理节点）和多个datanode（工作节点）

**缓存块**

通常datanode从磁盘读取块，但对于访问频繁的文件，其对应的块可能被显式的缓存在datanode的内存中，以堆外块缓存的形式存在

## Hadoop文件系统

