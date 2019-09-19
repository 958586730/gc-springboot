package com.yfc.gc.config.lock;

import com.yfc.gc.core.util.lock.Lock;
import com.yfc.gc.core.util.lock.LockKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class RedisLock implements Lock {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean lock(LockKey lockKey) {
        Objects.requireNonNull(lockKey, "lockKey is null");
        Objects.requireNonNull(lockKey.timeUnit(), "timeUnit is null");
        Long disabledTime = (Long) redisTemplate.opsForValue().get(lockKey.stringKey());
        // 有锁
        if(disabledTime != null) {
            // 还没有失效
            if(System.currentTimeMillis() < disabledTime){
                log.debug("redis-lock({})-锁失效剩余时间:{}", lockKey.stringKey(), disabledTime - System.currentTimeMillis());
                return false;
            }
            // 已经失效
            else{
                log.debug("redis-lock({})-超时", lockKey.stringKey());
                unLock(lockKey);
            }
        }
        log.debug("redis-lock({})-设置超时时间:{}", lockKey.stringKey(), lockKey.timeUnit().toMillis(lockKey.timeOut()));
        return redisTemplate.opsForValue().setIfAbsent(lockKey.stringKey()
                , lockKey.timeUnit().toMillis(lockKey.timeOut()) + System.currentTimeMillis());
    }

    @Override
    public void unLock(LockKey lockKey) {
        log.debug("redis-lock({})-解锁", lockKey.stringKey());
        redisTemplate.delete(lockKey.stringKey());
    }
}
