package com.yfc.gc.core.exception;

/**
 * @ClassName: UserNameException
 * @Description: 用户名重复异常
 * @Author liuhuixaing
 * @Date 2019/1/9 16:21
 * @Version 1.0
 */
public class UserNameException extends BaseRuntimeException {
    public UserNameException(String message, Object... args) {
        super(message, args);
    }

    public UserNameException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Override
    public String getMessage() {
        return "用户名异常: " + super.getMessage();
    }
}

