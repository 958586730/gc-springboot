package com.yfc.gc.core.exception;

public class SystemExeption extends BaseRuntimeException {

    public SystemExeption(String message, Object... args) {
        super(message, args);
    }

    public SystemExeption(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
}
