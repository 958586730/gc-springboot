package com.yfc.gc.config.advice;

import com.yfc.gc.core.password.Des;
import com.yfc.gc.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.*;
import java.lang.reflect.Type;

@RestControllerAdvice(basePackages="com.yfc.gc")
@Slf4j
public class RequestBodyFilter extends RequestBodyAdviceAdapter {

    @Value("${http.encryption.request}")
    private boolean encryption;

    @Value("${http.encryption.key}")
    private String key;

    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        if(!encryption){
            return inputMessage;
        }

        // 或者请求头不是application/json
        if(inputMessage.getHeaders().getContentType() != MediaType.APPLICATION_JSON){
            log.debug("未解密, contentType:{}", inputMessage.getHeaders().getContentType());
            return inputMessage;
        }
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = inputMessage.getBody();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            log.debug(ex.getMessage());
            throw ex;
        }finally{
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        if(ObjectUtil.notEmpty(stringBuilder)){
            try {
                stringBuilder = new StringBuilder(Des.desDecrypt(stringBuilder.toString(), key));
            } catch (Exception e) {
                log.error("参数解密失败:{}", stringBuilder);
                stringBuilder = new StringBuilder();
            }
        }
        String body = stringBuilder.toString();
        return new HttpInputMessage(){
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(body.getBytes());
            }
        };
    }

}
