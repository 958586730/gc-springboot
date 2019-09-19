package com.yfc.gc.core.exception;

public class HttpException extends BaseRuntimeException {

    public HttpException(String message, Object... args) {
        super(message, args);
    }

    public HttpException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
