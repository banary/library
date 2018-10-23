package com.banary.cache.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.ehcache.core.EhcacheManager;

import java.util.concurrent.TimeUnit;

/**
 * @Description 缓存工厂
 * @Author eden
 * @Date 2018/9/12 上午11:39
 */
public class GuavaCacheFactory {

    private Cache<String, Object> defaultCache;

    private GuavaCacheFactory(){
        this.defaultCache = buildGuavaCache();
    }

    private static class GuavaCacheFactoryHolder{
        private static final GuavaCacheFactory INSTANCE = new GuavaCacheFactory();
    }

    public static final GuavaCacheFactory getInstance(){
        return GuavaCacheFactoryHolder.INSTANCE;
    }

    public Cache<String, Object> getDefaultCache() {
        return defaultCache;
    }

    public GuavaCacheFactory setDefaultCache(Cache<String, Object> defaultCache) {
        this.defaultCache = defaultCache;
        return this;
    }

    public static final Cache<String, Object> getDefaultSingleCache(){
        return getInstance().getDefaultCache();
    }

    /**
     * 构建guava缓存, 可配置参数
     * 1. 并发级别 .concurrencyLevel(4)
     * 2. 基于容量的回收策略 .maximumSize(10000)
     * 3. TTL时间 .expireAfterWrite(10, TimeUnit.SECONDS)，只有在写缓存的时候才会更新生存时间
     * 4. TTI时间 .expireAfterAccess(10, TimeUnit.SECONDS) 写和读时都是会刷新空闲时间，空闲时间过长会导致脏数据存在很长时间，建议设置TTL时间
     * 5. .weakKeys() .weakValues() 设置弱引用缓存
     * 6. 设置软引用缓存 .softValues()
     * 7. 主动失效某些缓存 .invalidate()/.invalidateAll()/.invalidateAll()
     * 8. recordStats: 启动记录统计信息，比如命中率
     *
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Cache<K, V> buildGuavaCache(){
        return CacheBuilder.newBuilder()
                .concurrencyLevel(4)        // 并发级别
                .expireAfterWrite(10, TimeUnit.SECONDS)     // TTL时间
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10000)         // 容量策略
                .recordStats()
                .build();
    }

}
