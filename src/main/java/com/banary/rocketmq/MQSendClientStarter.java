package com.banary.rocketmq;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 下午5:07
 */
public class MQSendClientStarter {

    @Resource
    private ProducerConfig producerConfig;

    @PostConstruct
    private void init(){

    }
}
