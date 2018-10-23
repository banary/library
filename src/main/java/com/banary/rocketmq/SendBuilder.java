package com.banary.rocketmq;

import org.apache.rocketmq.client.producer.MessageQueueSelector;

import java.util.Map;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/7 下午4:05
 */
public class SendBuilder implements MQSendService{

    private String topic;
    private String tag;
    private Map<String, Object> map;
    private StoreMode storeMode;
    private SendMode sendMode;
    private MQSendService mqSendService;
    private MessageQueueSelector messageQueueSelector;

    public String getTopic() {
        return topic;
    }

    public SendBuilder setTopic(String topic) {
        this.topic = topic;
        return this;
    }

    public String getTag() {
        return tag;
    }

    public SendBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public SendBuilder setMap(Map<String, Object> map) {
        this.map = map;
        return this;
    }

    public StoreMode getStoreMode() {
        return storeMode;
    }

    public SendBuilder setStoreMode(StoreMode storeMode) {
        this.storeMode = storeMode;
        return this;
    }

    public SendMode getSendMode() {
        return sendMode;
    }

    public SendBuilder setSendMode(SendMode sendMode) {
        this.sendMode = sendMode;
        return this;
    }

    public MQSendService getMqSendService() {
        return mqSendService;
    }

    public SendBuilder setMqSendService(MQSendService mqSendService) {
        this.mqSendService = mqSendService;
        return this;
    }

    public MessageQueueSelector getMessageQueueSelector() {
        return messageQueueSelector;
    }

    public SendBuilder setMessageQueueSelector(MessageQueueSelector messageQueueSelector) {
        this.messageQueueSelector = messageQueueSelector;
        return this;
    }

    @Override
    public boolean send(SendBuilder sendBuilder) {
        return this.mqSendService.send(this);
    }
}
