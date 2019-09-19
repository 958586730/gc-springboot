package com.yfc.gc.core.util.http;

import com.yfc.gc.core.consts.SystemConst;
import com.yfc.gc.core.exception.HttpException;
import com.yfc.gc.core.util.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Slf4j
public class HttpBuild<T> implements SystemConst, ExecuteInterface<T> {

    public static final String APPLICATION_JSON = "application/json;charset=utf-8";
    public static final String TEXT_XML         = "text/xml;charset=utf-8";
    public static final String APPLICATION_FORM = "application/x-www-form-urlencoded;charset=utf-8";
    /**
     * 请求header参数
     */
    private Map<String, Object> headers = new HashMap<>();

    /**
     * 请求url参数
     */
    private Map<String, Object> urlParam = new HashMap<>();

    /**
     * 请求body参数
     */
    private Object body;

    /**
     * 请求类型
     */
    private HttpType httpMethod;

    /**
     * 请求url
     */
    private String url;

    /**
     * 错误重试次数
     */
    private int errorResend;

    //连接超时时间
    private int socketTimeout = 10000;

    //传输超时时间
    private int connectTimeout = 30000;

    /**
     * 错误回调
     */
    private ErrorFunction<HttpBuild<T>, T> errorFunction;

    /**
     * 正确回调
     */
    private SuccessFunction<HttpBuild<T>, T> successFunction;

    /**
     * 验证返回值
     */
    private ValidateFunction<T> validateFunction;

    private int successHttpStatus = HttpStatus.SC_OK;

    private Http<T> http;

    private String contentType = APPLICATION_JSON;

    /**
     * @Author WangYX
     * @Description 证书
     * @Date 2019/1/11 上午12:53
     */
    private SSLConnectionSocketFactory sslConnectionSocketFactory;

    private HttpBuild(){
        this.successFunction = ((httpBuild, response) ->JSONUtil.toObj(response, http.getResultType()));
        this.errorFunction = ((httpBuild, e) -> {
            if(e instanceof HttpException){
                throw (HttpException) e;
            }
            throw new HttpException(e, e.getMessage());
        });
        this.validateFunction = (response -> {});
    }

    public static <T> HttpBuild<T> post(String url, Class<T> clazz){
        HttpBuild<T> http = new HttpBuild<>();
        http.url = url;
        http.httpMethod = HttpType.POST;
        return http;
    }

    public static <T> HttpBuild<T> get(String url, Class<T> clazz){
        HttpBuild<T> http = new HttpBuild<>();
        http.url = url;
        http.httpMethod = HttpType.GET;
        return http;
    }

    @Override
    public Optional<T> execute(Type resultType){
        return (this.http = new Http<>(this, resultType)).send();
    }
    
    public HttpBuild<T> addUrlParam(String key, Object val){
        this.urlParam.put(key, val);
        return this;
    }

    public HttpBuild<T> addUrlParam(Map<String, Object> params){
        this.urlParam.putAll(params);
        return this;
    }


    public HttpBuild<T> addHeader(String key, Object val){
        this.headers.put(key, val);
        return this;
    }

    public HttpBuild<T> addHeader(Map<String, Object> headers){
        this.headers.putAll(headers);
        return this;
    }

    public HttpBuild<T> addBody(Object body){
        this.body = body;
        return this;
    }

    public HttpBuild<T> addErrCallBack(ErrorFunction<HttpBuild<T>, T> errorFunction){
        this.errorFunction = errorFunction;
        return this;
    }

    public HttpBuild<T> addSuccCallBack(SuccessFunction<HttpBuild<T>, T> successFunction){
        this.successFunction = successFunction;
        return this;
    }

    public HttpBuild<T> addValidate(ValidateFunction<T> validateFunction){
        this.validateFunction = validateFunction;
        return this;
    }

    public HttpBuild<T> addSuccHttpStatus(int successHttpStatus){
        this.successHttpStatus = successHttpStatus;
        return this;
    }

    public HttpBuild<T> addSocketTimeout(int socketTimeout){
        this.socketTimeout = socketTimeout;
        return this;
    }

    public HttpBuild<T> addConnectTimeout(int connectTimeout){
        this.connectTimeout = connectTimeout;
        return this;
    }

    public HttpBuild<T> addSSLConnectionSocketFactory(SSLConnectionSocketFactory sslConnectionSocketFactory){
        this.sslConnectionSocketFactory = sslConnectionSocketFactory;
        return this;
    }

    public HttpBuild<T> addErrReSend(int i){
        this.errorResend = i;
        return this;
    }


    public HttpBuild<T> json(){
        this.contentType = APPLICATION_JSON;
        return this;
    }

    public HttpBuild<T> xml(){
        this.contentType = TEXT_XML;
        return this;
    }

    public HttpBuild<T> form(){
        this.contentType = APPLICATION_FORM;
        return this;
    }

    public enum HttpType{
        GET, POST, DELETE, PUT
    }
}
