package com.banary.rocketmq;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 下午4:15
 */
public class MQTest {

    public static void main(String[] args) {
        ProducerConfig producerConfig = new ProducerConfig();
        producerConfig.setGroupName("TEST")
            .setInstanceName("TEST")
            .setNamesrvAddr("localhost:9876");

        ProducerManager.getInstance().createAndRegisterProducer(producerConfig);

        ProducerManager.getInstance().getProducer("TEST");

    }
}
