package com.banary.rocketmq;

import java.util.Map;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 上午11:29
 */
public interface MQSendService {

    boolean send(SendBuilder sendBuilder);
}
