package com.yfc.gc.core.exception;

/**
 * @ClassName: PkIdException
 * @Description: 主键id异常
 * @Author liuhuixaing
 * @Date 2019/1/5 14:46
 * @Version 1.0
 */
public class PkIdException extends BaseRuntimeException {
    public PkIdException(String message, Object... args) {
        super(message, args);
    }
    public PkIdException(Throwable cause, String message, Object... args) {
        super(cause, message, args);
    }
    @Override
    public String getMessage() {
        return "公司编码冲突: "+ super.getMessage();
    }
}
