package com.yfc.gc.core.util.cache;

import java.util.Optional;
import java.util.function.Supplier;

public interface Cache {

    /**
     * 添加缓存数据
     * @param cacheKey
     * @param val
     * @return
     */
    void set(CacheKey cacheKey, Object val);

    /**
     * 获得缓存数据
     * @param cacheKey
     * @param <T>
     * @return
     */
    <T> Optional<T> get(CacheKey cacheKey);
    <T> Optional<T> get(CacheKey cacheKey, Class<T> clazz);

    default <T> T get(CacheKey cacheKey, Supplier<T> supplier){
        Optional<T> res = get(cacheKey);
        if(res.isPresent()){
            return res.get();
        }
        T val;
        set(cacheKey, val = supplier.get());
        return val;
    }


    /**
     * 删除缓存数据
     * @param cacheKey
     * @return
     */
    void del(CacheKey cacheKey);

}
