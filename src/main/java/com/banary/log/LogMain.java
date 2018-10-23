package com.banary.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author eden
 * @Date 2018/10/17 上午10:47
 */
public class LogMain {

    private static final Logger logger = LoggerFactory.getLogger(LogMain.class);

    public static void main(String[] args) {
        for(;;){
            logger.info("我要输出的info日志---------------》》》》》" + System.nanoTime());
            logger.error("我要输出的error日志---------------》》》》》" + System.nanoTime());
        }
    }
}
