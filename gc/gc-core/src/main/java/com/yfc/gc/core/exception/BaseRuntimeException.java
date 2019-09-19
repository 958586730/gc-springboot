package com.yfc.gc.core.exception;

public abstract class BaseRuntimeException extends RuntimeException {

    private Object[] args;

    private BaseRuntimeException child;

    public BaseRuntimeException(String message, Object... args) {
        super(message);
        this.args = args;
    }

    public BaseRuntimeException(Throwable cause, String message, Object... args) {
        super(message, cause);
        this.args = args;
    }

    public BaseRuntimeException addChild(BaseRuntimeException child){
        this.child = child;
        return this;
    }

    @Override
    public String getMessage() {
        if(child == null) {
            return super.getMessage();
        }else{
            return String.format("%s[%s]", super.getMessage(), child.getMessage());
        }
    }
}
