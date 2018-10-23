package com.banary.mode.two_phase_termination;

/**
 * @Description 抽象可停止线程
 * @Author eden
 * @Date 2018/7/27 下午5:27
 */
public abstract class AbstractTerminatableThread implements Terminatable {

    /**
     * 设置线程停止标志，并发送听着信号给目标线程
     */
    @Override
    public void terminate() {

    }

    /**
     * 子类扩展方法，实现线程停止时所需的一些额外操作
     */
    protected abstract void doTerminate();

    /**
     * 线程处理逻辑方法，子类扩展实现线程的具体处理逻辑
     */
    protected abstract void doRun();

    /**
     * 子类扩展方法，实现线程停止后可能需要的一些清理动作
     */
    protected abstract void doCleanUp();
}
