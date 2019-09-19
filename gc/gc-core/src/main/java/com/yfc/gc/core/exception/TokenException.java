package com.yfc.gc.core.exception;

import lombok.Data;

/**
 * @Author WangYX
 * @Description //TODO token异常
 * @Date 2018/12/25 上午11:56
 **/
@Data
public class TokenException extends BaseRuntimeException {

    private String code;

    public TokenException(String code, String message, Object... args) {
        super(message, args);
        this.code = code;
    }

    public TokenException(String message, Object... args) {
        super(message, args);
    }

    public TokenException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }

    @Override
    public String getMessage() {
        return "token error: "+ super.getMessage();
    }
}
