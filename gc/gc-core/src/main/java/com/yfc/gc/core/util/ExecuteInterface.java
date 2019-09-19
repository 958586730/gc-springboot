package com.yfc.gc.core.util;

import java.lang.reflect.Type;
import java.util.Optional;

/**
 * 执行器
 * @param <T>
 */
public interface ExecuteInterface<T> {

    Optional<T> execute(Type resultType);

}
