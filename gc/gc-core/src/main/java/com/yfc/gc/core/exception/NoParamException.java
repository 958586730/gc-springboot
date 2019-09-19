package com.yfc.gc.core.exception;

/**
 * 缺少参数异常
 */
public class NoParamException extends BaseRuntimeException {

    public NoParamException(String message, Object... args) {
        super(message, args);
    }

    public NoParamException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
