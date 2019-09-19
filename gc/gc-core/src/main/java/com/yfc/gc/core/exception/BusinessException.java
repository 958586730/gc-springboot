package com.yfc.gc.core.exception;

/**
 * @ClassName: BusinessException
 * @Description: 业务逻辑异常
 * @Author liuhuixaing
 * @Date 2019/2/20 17:40
 * @Version 1.0
 */
public class BusinessException extends BaseRuntimeException {
    public BusinessException(String message, Object... args) {
        super(message, args);
    }

    public BusinessException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Override
    public String getMessage() {
        return "业务操作异常: " + super.getMessage();
    }
}
