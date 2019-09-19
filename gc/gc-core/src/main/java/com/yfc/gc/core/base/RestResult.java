package com.yfc.gc.core.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult<T> {

    private String msg;

    private String code;

    private boolean success;

    private T data;

    private long time = System.currentTimeMillis();

    public static <T> RestResult<T> success(T data){
        RestResult restResult = new RestResult();
        restResult.setMsg("请求成功!");
        restResult.setSuccess(true);
        restResult.setData(data);
        return restResult;
    }

    public static <T> RestResult<T> error(String msg){
        return error(null, msg);
    }

    public static <T> RestResult<T> error(String code, String msg){
        RestResult restResult = new RestResult();
        restResult.setMsg(msg);
        restResult.setCode(code);
        restResult.setSuccess(false);
        return restResult;
    }
}
