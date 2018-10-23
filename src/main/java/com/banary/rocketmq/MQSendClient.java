package com.banary.rocketmq;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 下午4:15
 */
public interface MQSendClient {

    boolean send(SendBuilder sendBuilder);
}
