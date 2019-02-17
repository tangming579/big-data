# YARN

Apache YARN（Yet Another Resource Negotiator）是Hadoop集群资源管理系统

YARN总体上仍然是master/slave结构，在整个资源管理框架中，resourcemanager为master，nodemanager是slave。Resourcemanager负责对各个nademanger上资源进行统一管理和调度。当用户提交一个应用程序时，需要提供一个用以跟踪和管理这个程序的ApplicationMaster，它负责向ResourceManager申请资源，并要求NodeManger启动可以占用一定资源的任务。由于不同的ApplicationMaster被分布到不同的节点上，因此它们之间不会相互影响。

## YARN运行机制

<div>
    <image src="img/YARN.png"></image>
</div>
## YARN架构

<div>
    <image src="img/yarn_architecture.gif"></image>
</div>



1. ResourceManager：RM
   - 整个集群同一时间提供服务的RM只有一个，负责集群资源的统一管理和调度
   - 处理客户端的请求：提交一个作业、杀死一个作业
   - 监控NM，一旦某个NM挂了，那么该NM上运行的任务需要通知AM来如何进行处理
2. NodeManager：NM
   - 整个集群中有多个，负责自己自身节点资源管理和使用
   - 定时向RM汇报本节点的资源使用情况
   - 接收并处理来自RM的各种命令：启动Container
   - 处理来自AM的命令
   - 单个节点的资源管理
3. ApplicationMaser：AM
   - 每个应用程序对应一个MR、Spark，负责应用程序的管理
   - 为应用程序向RM申请资源（core、memory），分配给内部task
   - 需要与NM通信：启动/停止task，task是运行在container里面，AM也是运行在container里面
4. Container
   - 封装了CPU、Memory等资源的一个容器
   - 是一个任务运行环境的抽象

5. Client
   - 提交作业
   - 查询作业的运行进度
   - 杀死作业

