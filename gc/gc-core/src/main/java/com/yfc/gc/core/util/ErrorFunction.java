package com.yfc.gc.core.util;

@FunctionalInterface
public interface ErrorFunction<E extends ExecuteInterface<T>, T> {

    T callBack(E execute, Exception e);

}
