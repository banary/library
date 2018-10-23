package com.banary.rocketmq;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/6 上午10:31
 */
public class ProducerConfig {

    private static final int DEFAULT_RETRYTIMESWHENSENDFAILED = 3;

    private String prefix;
    private String groupName;
    private String instanceName;
    private Integer retryTimesWhenSendFailed = DEFAULT_RETRYTIMESWHENSENDFAILED;
    private String namesrvAddr;

    public String getPrefix() {
        return prefix;
    }

    public ProducerConfig setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public ProducerConfig setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public ProducerConfig setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public Integer getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }

    public ProducerConfig setRetryTimesWhenSendFailed(Integer retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
        return this;
    }

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public ProducerConfig setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
        return this;
    }
}
