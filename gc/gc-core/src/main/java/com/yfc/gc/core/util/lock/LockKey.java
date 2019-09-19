package com.yfc.gc.core.util.lock;

import java.util.concurrent.TimeUnit;

public interface LockKey {

    /**
     * 获得锁的key
     * @return
     */
    String key();

    /**
     * 获得锁超时时间
     * @return
     */
    default long timeOut(){
        return 60L;
    }

    /**
     * 获得锁超时时间单位
     * @return
     */
    default TimeUnit timeUnit(){
        return TimeUnit.MINUTES;
//        return TimeUnit.SECONDS;
    }

    default String stringKey(){
        return "lock_" + key();
    }
}
