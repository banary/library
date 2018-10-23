package com.banary.rocketmq;

import org.apache.rocketmq.client.impl.MQClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 下午5:34
 */
public class RocketMQSendClient implements MQSendClient{

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMQSendClient.class);

    @Override
    public boolean send(SendBuilder sendBuilder) {
        ProducerManager.getInstance().getProducer(sendBuilder.getTopic());
        return false;
    }
}
