package com.banary.mode.master_slave;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author eden
 * @Date 2018/7/27 下午3:30
 */
public class Master {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                throw new RuntimeException("错误了");
            }
        });
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("成功了");
            }
        });
    }
}
