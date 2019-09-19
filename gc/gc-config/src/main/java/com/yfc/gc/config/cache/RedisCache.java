package com.yfc.gc.config.cache;

import com.yfc.gc.core.util.cache.Cache;
import com.yfc.gc.core.util.cache.CacheKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
@Slf4j
public class RedisCache implements Cache {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void set(CacheKey cacheKey, Object val) {
        if(cacheKey.timeOut() <= 0) {
            redisTemplate.opsForValue().set(cacheKey.stringKey(), val);
        }else{
            redisTemplate.opsForValue().set(cacheKey.stringKey(), val, cacheKey.timeOut(), cacheKey.timeUnit());
        }
    }

    @Override
    public <T> Optional<T> get(CacheKey cacheKey) {
        return Optional.ofNullable((T) redisTemplate.opsForValue().get(cacheKey.stringKey()));
    }

    @Override
    public <T> Optional<T> get(CacheKey cacheKey, Class<T> clazz) {
        return get(cacheKey);
    }

    @Override
    public void del(CacheKey cacheKey) {
        redisTemplate.delete(redisTemplate.keys(cacheKey.stringKey()));
    }
}
