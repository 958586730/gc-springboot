package com.yfc.gc.config.advice;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.yfc.gc.config.RestExceptionAdvice;
import com.yfc.gc.core.base.RestResult;
import com.yfc.gc.core.thread.Keys;
import com.yfc.gc.core.thread.ThreadData;
import com.yfc.gc.core.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages="com.yfc.gc"
        , basePackageClasses=RestExceptionAdvice.class)
@Slf4j
public class ResponseBodyFilter implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(log.isDebugEnabled()) {
            log.debug("responseBody id: -->{}<--, 返回:{}", ThreadData.get(Keys.REQUEST_ID), JSONUtil.toJson(body));
        }
        // 清空线程对象
        ThreadData.clear();
        return result(body);
    }

    protected Object result(Object body){
        if(body instanceof RestResult){
            return body;
        }

        if(body instanceof Exception){
            return RestResult.error(((Exception) body).getMessage());
        }else{
            return RestResult.success(getData(body));
        }
    }

    /**
     * 获得出参
     * @Title: getData
     * @param @param data
     * @param @return    设定文件
     * @return Object    返回类型
     * @throws
     */
    private static Object getData(Object data){
        if(data instanceof Page){
            return new PageInfo<>((Page) data);
        }
        return data;
    }

}
