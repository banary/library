package com.banary.cache.guava;

import com.google.common.cache.Cache;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/12 上午11:20
 */
public class CacheDemo {

    public static String value = "demo";

    public static void main(String[] args) {
        guavaCacheDemo();
        System.out.println(010);
    }

    public static GuavaCacheService getGuavaCacheService(){
        GuavaCacheService service = new GuavaCacheService();
        service.init();
        return service;
    }

    public static String loadFromSoR(String key){
        return value;
    }

    public static void writeToSoR(String value){
        CacheDemo.value = value;
    }

    /**
     * cache-aside模式
     * 1. 适用于AOP模式实现
     * 2. 缺点：可能存在并发更新的情况
     *  2.1 如果是用户维度的数据，并发的情况很少，可以不考虑这个问题，加上过期时间来解决
     *  2.2 读场景可以考虑使用一致性哈希，将相同的操作负载均衡到同一个实例，或者设置比较短的过期时间
     */
    public static void guavaCacheDemo(){
        GuavaCacheService service = getGuavaCacheService();
        String key = "demo";

        // 读场景
        Runnable runnable = () -> {
            Object value = service.get(key);
            if(value == null){
                value = loadFromSoR(key);
                service.put(key, value);
            }
            System.out.println("当前缓存值：" + service.get(key));
        };
        runnable.run();

        // 写场景
        // 方案一： 1. 将数据写入DB
        // 2.1 写入DB成功后同步写入缓存
        writeToSoR("new value1");
        service.put(key, "new value1");
        runnable.run();

        // 2.2 将原缓存删除，下次读时重新加载
        writeToSoR("new value2");
        service.delete(key);
        runnable.run();
    }

    /**
     * Cache-As-SoR
     *  read-through
     *  write-through
     *  write-behind
     */


}
