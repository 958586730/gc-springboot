package com.yfc.gc.core.util;

@FunctionalInterface
public interface SuccessFunction<E extends ExecuteInterface<T>, T> {

    T callBack(E execute, String response);

}
