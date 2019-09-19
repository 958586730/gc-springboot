package com.yfc.gc.core.util.lock;

import java.util.concurrent.TimeUnit;

public interface Lock {

    /**
     * 获取锁
     * @param lockKey 获取锁的key
     */
    boolean lock(LockKey lockKey);

    /**
     * 获得锁
     * @param lockKey
     * @param time
     * @param waitUnit
     * @return
     */
    default boolean lock(LockKey lockKey, long time, TimeUnit waitUnit){
        long waitTime = System.currentTimeMillis() + waitUnit.toMillis(time);
        do{
            if(lock(lockKey)){
                return true;
            }
        }while (System.currentTimeMillis() < waitTime);
        return false;
    }

    /**
     * 解除锁
     * @param lockKey
     */
    void unLock(LockKey lockKey);

}
