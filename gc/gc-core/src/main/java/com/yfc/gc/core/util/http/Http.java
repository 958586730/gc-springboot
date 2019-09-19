package com.yfc.gc.core.util.http;

import com.yfc.gc.core.consts.SystemConst;
import com.yfc.gc.core.exception.HttpException;
import com.yfc.gc.core.util.JSONUtil;
import com.yfc.gc.core.util.ObjectUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Getter
public class Http<T> implements SystemConst {


    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, (x509Certificates, s)->true);
            sslsf = new SSLConnectionSocketFactory(
                    builder.build()
                    , new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}
                    , null
                    , NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HttpBuild<T> httpInfo;

    private Type resultType;

    private boolean success;

    public Http(HttpBuild<T> httpInfo, Type resultType){
        this.httpInfo = httpInfo;
        this.resultType = resultType;
        log.debug("http-run 返回类型:{}", resultType);
    }

    public Optional<T> send(){
        int i = httpInfo.getErrorResend();
        Optional<T> res;
        do{
            res = build();
        }while (i > 0 && !success);
        return res;
    }

    protected Optional<T> build(){
        HttpRequestBase httpMessage = getHttpRequestBase();
        setHttpInfo(httpMessage);
        try (CloseableHttpClient httpClient = getHttpClient()){
            long begin = System.currentTimeMillis();
            HttpResponse httpResponse = httpClient.execute(httpMessage);
            log.debug("http-run 请求时间:{}", System.currentTimeMillis() - begin);
            String resultStr = EntityUtils.toString(httpResponse.getEntity(), PROJECT_CHARSET);
            if(httpResponse.getStatusLine().getStatusCode() != httpInfo.getSuccessHttpStatus()){
                log.error("http-run 状态:{}", httpResponse.getStatusLine().getStatusCode());
                success = false;
                return Optional.ofNullable(httpInfo.getErrorFunction().callBack(httpInfo, new HttpException(resultStr)));
            }
            if(log.isDebugEnabled()) {
                log.debug("http-run response:{}", StringUtils.length(resultStr) > 1000 ? resultStr.substring(0, 999): resultStr);
            }
            T res = httpInfo.getSuccessFunction().callBack(httpInfo, resultStr);
            httpInfo.getValidateFunction().validate(res);
            success = true;
            return Optional.ofNullable(res);
        } catch (IOException e) {
            success = false;
            httpInfo.getErrorFunction().callBack(httpInfo, new HttpException(e, e.getMessage()));
        }
        return Optional.empty();
    }

    private void setHttpInfo(HttpRequestBase httpMessage) {
        httpMessage.setConfig(
                RequestConfig.custom()
                        .setSocketTimeout(httpInfo.getSocketTimeout())
                        .setConnectTimeout(httpInfo.getConnectTimeout())
                        .build()
        );
        // heads
        if(ObjectUtil.notEmpty(httpInfo.getHeaders())){
            log.debug("http-run headers:{}", JSONUtil.toJson(httpInfo.getHeaders()));
            httpInfo.getHeaders().entrySet().forEach(entry->httpMessage.setHeader(entry.getKey(), getHeadVal(entry.getKey(), entry.getValue())));
        }
        // body
        if(httpInfo.getBody() != null && httpMessage instanceof HttpPost){
            String body = httpInfo.getBody() instanceof String ? Objects.toString(httpInfo.getBody()) : JSONUtil.toJson(httpInfo.getBody());
            StringEntity stringEntity = new StringEntity(body, PROJECT_CHARSET);
            stringEntity.setContentType(httpInfo.getContentType());
            ((HttpPost) httpMessage).setEntity(stringEntity);
            log.debug("http-run body:{}", JSONUtil.toJson(httpInfo.getBody()));
        }
        // form-param
        if(ObjectUtil.notEmpty(httpInfo.getUrlParam()) && httpMessage instanceof HttpPost){
            List<? extends NameValuePair> parameters =
            httpInfo.getUrlParam()
                    .entrySet()
                    .stream()
                    .map(param->new BasicNameValuePair(param.getKey(), param.getValue() instanceof String ? Objects.toString(param.getValue()) : JSONUtil.toJson(param.getValue())))
                    .collect(Collectors.toList());
            try {
                ((HttpPost) httpMessage).setEntity(new UrlEncodedFormEntity(parameters, PROJECT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    private String getHeadVal(String key, Object val){
        return Objects.toString(val, "");
    }

    private CloseableHttpClient getHttpClient(){
        if(httpInfo.getUrl().startsWith("https")){
            if(httpInfo.getSslConnectionSocketFactory() == null) {
                return HttpClients.custom()
                        .setSSLSocketFactory(sslsf)
                        .setConnectionManager(cm)
                        .setConnectionManagerShared(true)
                        .build();
            }else{
                ConnectionConfig config = ConnectionConfig.custom().setCharset(Charset.forName("UTF-8")).build();

                Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .register("https", httpInfo.getSslConnectionSocketFactory()).build();

                PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
                connManager.setDefaultConnectionConfig(config);
                return HttpClients.custom().setDefaultConnectionConfig(config).setConnectionManager(connManager).build();
            }
        }else{
            return HttpClients.createDefault();
        }
    }

    private HttpRequestBase getHttpRequestBase(){
        try {
            URIBuilder uri = new URIBuilder(httpInfo.getUrl());
            if(!ObjectUtil.equals(HttpBuild.APPLICATION_FORM, httpInfo.getContentType())
                    && ObjectUtil.notEmpty(httpInfo.getUrlParam())){
                httpInfo.getUrlParam().entrySet().stream().forEach(entry->
                        uri.addParameter(entry.getKey()
                                , entry.getValue() instanceof String ? (String) entry.getValue() : JSONUtil.toJson(entry.getValue())));
            }
            log.debug("http-run 准备发送请求:{},类型:{}", uri.build().toString(), httpInfo.getHttpMethod());

            switch (httpInfo.getHttpMethod()){
                case GET:
                    return new HttpGet(uri.build());
                case POST:
                    return new HttpPost(uri.build());
                case PUT:
                    return new HttpPut(uri.build());
                case DELETE:
                    return new HttpDelete(uri.build());
                default:
                    throw new HttpException(" no method! ");
            }
        } catch (URISyntaxException e) {
            throw new HttpException(e, " url: "+ httpInfo.getUrl());
        }
    }

}
