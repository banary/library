package com.banary.cache.guava;


import com.google.common.cache.Cache;
import com.sun.tools.javac.util.Assert;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/13 上午11:50
 */
public class GuavaCacheService implements CacheService<String, Object> {

    private Cache<String, Object> cache;

    public void init(){
        this.cache = GuavaCacheFactory.getDefaultSingleCache();
    }

    @Override
    public void put(String key, Object value) {
        Assert.checkNonNull(key);
        this.cache.put(key, value);
    }

    @Override
    public Object get(String key) {
        return this.cache.getIfPresent(key);
    }

    @Override
    public Object get(String key, CacheCallback<Object> cacheCallback, Object... context) {
        return null;
    }

    @Override
    public void delete(String key) {
        this.cache.invalidate(key);
    }
}
