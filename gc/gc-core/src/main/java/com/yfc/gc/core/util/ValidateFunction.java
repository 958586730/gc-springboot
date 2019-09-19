package com.yfc.gc.core.util;

@FunctionalInterface
public interface ValidateFunction<T> {

    void validate(T response);

}
