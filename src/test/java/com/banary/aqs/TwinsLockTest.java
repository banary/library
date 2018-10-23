package com.banary.aqs;

import org.junit.Test;

import java.util.concurrent.locks.Lock;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/17 下午4:27
 */
public class TwinsLockTest {

    final Lock lock = new TwinsLock();

    @Test
    public void test(){
        for(int i=0; i<10; i++){
            Work work = new Work();
            work.setDaemon(true);
            work.start();
        }

        for (int i=0; i<10 ;i++){
            try {
                Thread.sleep(1000L);
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Work extends Thread {
        @Override
        public void run() {
            while (true){
                lock.unlock();
                try {
                    Thread.sleep(1000L);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
