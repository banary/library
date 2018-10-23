package com.banary.cache.guava;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;

import java.time.Duration;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/12 下午5:51
 */
public class EhcacheFactory {

    private Cache<String, Object> defaultCache;

    private void EhcacheFactory(){
        this.defaultCache = buildEhcache(String.class, Object.class);
    }

    private static class EhcacheFactoryHolder{
        private static final EhcacheFactory INSTANCE = new EhcacheFactory();
    }

    public static final EhcacheFactory getInstance(){
        return EhcacheFactoryHolder.INSTANCE;
    }

    public Cache<String, Object> getDefaultCache() {
        return defaultCache;
    }

    public EhcacheFactory setDefaultCache(Cache<String, Object> defaultCache) {
        this.defaultCache = defaultCache;
        return this;
    }

    public static final Cache<String, Object> getDefaultSingleCache(){
        return getInstance().getDefaultCache();
    }

    /**
     * 1. ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, EntryUnit.ENTRIES) 使用堆内存，内存大小
     * 2. .withDispatcherConcurrency(4) 并发量
     * 3. .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(10))) 过期策略
     *
     * @param clazzK
     * @param clazzV
     * @param <K>
     * @param <V>
     * @return
     */
    public <K, V> Cache<K, V> buildEhcache(Class<K> clazzK, Class<V> clazzV){
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

        CacheConfigurationBuilder builder = CacheConfigurationBuilder.newCacheConfigurationBuilder(clazzK, clazzV,
                ResourcePoolsBuilder.newResourcePoolsBuilder().heap(100, EntryUnit.ENTRIES))
                .withDispatcherConcurrency(4)
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(10)));

        return cacheManager.createCache("ehcache", builder);
    }

}
