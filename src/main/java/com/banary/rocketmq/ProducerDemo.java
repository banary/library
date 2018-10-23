package com.banary.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author eden
 * @Date 2018/8/29 下午2:03
 */
public class ProducerDemo {

    public static void main(String[] args) {
        syncSend();
    }

    public static void syncSend(){
        DefaultMQProducer mqProducer = initMQProducer("localhost:9876", "SELF_TEST_TOPIC");
        mqProducer.setInstanceName("syncSendDemo");
        mqProducer.setRetryTimesWhenSendAsyncFailed(3);

        for(int i=0; i<1000; i++){
            try {
                Message message = new Message("SELF_TEST_TOPIC", "*", "CONTENT".getBytes(RemotingHelper.DEFAULT_CHARSET));
                mqProducer.send(message, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.println("SendStatus: " + sendResult.getSendStatus());
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            } catch (UnsupportedEncodingException e){
                e.printStackTrace();
            } catch (MQClientException e) {
                e.printStackTrace();
            } catch (RemotingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static DefaultMQProducer initMQProducer(String nameSrvAddr, String groupName){
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer(groupName);
        defaultMQProducer.setNamesrvAddr(nameSrvAddr);
        try {
            defaultMQProducer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return defaultMQProducer;
    }
}
