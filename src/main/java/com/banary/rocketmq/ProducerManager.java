package com.banary.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/6 上午10:32
 */
public class ProducerManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerManager.class);

    private static class ProducerManagerHolder{
        private static final ProducerManager INSTANCE = new ProducerManager();
    }

    private ConcurrentHashMap<String, DefaultMQProducer> producerMap = new ConcurrentHashMap();

    public static ProducerManager getInstance(){
        return ProducerManagerHolder.INSTANCE;
    }

    public void init(){
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("Init Producer manager at ----> {}", System.currentTimeMillis());
        }
    }

    public void createAndRegisterProducer(ProducerConfig producerConfig){
        String instanceName = producerConfig.getInstanceName();
        if(!this.producerMap.containsKey(instanceName)){
            synchronized (this){
                if (!this.producerMap.containsKey(instanceName)) {
                    DefaultMQProducer producer = new DefaultMQProducer(producerConfig.getGroupName());
                    producer.setNamesrvAddr(producerConfig.getNamesrvAddr());
                    producer.setInstanceName(producerConfig.getInstanceName());
                    producer.setRetryTimesWhenSendAsyncFailed(producerConfig.getRetryTimesWhenSendFailed());
                    try {
                        producer.start();
                    }catch (MQClientException e){
                        LOGGER.error("start producer error, producerConfig:{}, e:{}", producerConfig, e);
                    }
                    this.producerMap.putIfAbsent(producerConfig.getInstanceName(), producer);
                }
            }
        }
    }

    public void shutdownAndRemoveProducer(String instanceName){
        DefaultMQProducer producer = this.producerMap.get(instanceName);
        if(producer != null){
            producer.shutdown();
            this.producerMap.remove(instanceName);
        }
    }

    public void destroy(){
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("Destroy Producer manager at ----> {}", System.currentTimeMillis());
        }
    }

    public DefaultMQProducer getProducer(String instanceName){
        return this.producerMap.get(instanceName);
    }

}
