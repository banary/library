package com.banary.mode.two_phase_termination;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 线程停止标志
 * @Author eden
 * @Date 2018/7/27 下午5:32
 */
public class TerminationToken {

    /**
     * 用于指示目标线程可以停止了
     */
    private boolean toShutDown;

    /**
     * 用于反映目标线程还有多少数量未完成的任务，以支持等目标线程处理完器任务后再行停止
     */
    private AtomicInteger reservations;

    public boolean isToShutDown() {
        return toShutDown;
    }

    public void setToShutDown(boolean toShutDown) {
        this.toShutDown = toShutDown;
    }

    public AtomicInteger getReservations() {
        return reservations;
    }

    public void setReservations(AtomicInteger reservations) {
        this.reservations = reservations;
    }
}
