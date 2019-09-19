package com.yfc.gc.core.util.cache;

import java.util.concurrent.TimeUnit;

@FunctionalInterface
public interface CacheKey {

    /**
     * 获得缓存的key
     * @return
     */
    String key();

    default String prefix(){
        return "default";
    }

    /**
     * 获得缓存失效时间
     * @return
     */
    default long timeOut(){
        return -1L;
    }

    /**
     * 获得缓存失效时间单位
     * @return
     */
    default TimeUnit timeUnit(){
        return TimeUnit.MINUTES;
//        return TimeUnit.SECONDS;
    }

    default String stringKey(){
        return prefix() + key();
    }
}
