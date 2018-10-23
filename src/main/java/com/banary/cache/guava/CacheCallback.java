package com.banary.cache.guava;

import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @Description
 * @Author eden
 * @Date 2018/9/13 上午11:35
 */
@FunctionalInterface
public interface CacheCallback<V> {

    V load();

}

