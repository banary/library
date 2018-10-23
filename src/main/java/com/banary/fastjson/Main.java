package com.banary.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @Description
 * @Author eden
 * @Date 2018/8/10 上午10:38
 */
public class Main {

    public static void main(String[] args) {

        String testStr = "{\"data\":[{\"frameworkSource\":\"WEB\",\"ip\":\"127.0.0.1\",\"userId\":161,\"time\":1474169071000,\"businessType\":\"登录\"}],\"pageNo\":0,\"businessType\":\"CollectUserLogin\",\"startDate\":\"\"}";

        KafkaLog<CollectUserLogin> log = JSON.parseObject(testStr, new TypeReference<KafkaLog<CollectUserLogin>>() {});

        System.out.println(log.toString());
    }
}
