### 两阶段提交模式

- 过程
1. client 调用 ThreadOwner 的shutdown()
2. shutdown 调用 Terminatable 的terminate()
3. terminate将TerminationToken的toShutDown标志设置成true, 表示停止
4. terminate方法调用Terminatable的doTerminate()，使得子类可以为停止目标线程做一些必要的操作
5. TerminationToken的reservations==0时表示目标线程都已经处理完任务了，或者ThreadOwner在停止线程时
不关心其是否有未处理的任务。此时，terminate方法调用目标线程的interrupt(), 中断目标线程
6. 目标线程的terminate()调用结束
7. shutdown()返回，此时目标线程可能还在运行
8. 执行阶段由目标线程的run方法去检查TerminationToken的toShutDown标志和reservations，并捕获interrupt()
方法飘出的相关异常已决定是否停止线程。在线程停止前doCleanUp方法被调用

- demo
 告警功能