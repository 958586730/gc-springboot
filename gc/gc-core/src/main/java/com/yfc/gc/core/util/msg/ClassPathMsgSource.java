package com.yfc.gc.core.util.msg;

import com.yfc.gc.core.thread.Keys;
import com.yfc.gc.core.thread.ThreadData;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ClassPathMsgSource implements MsgSource {

    private Map<Locale, Properties> cache = new ConcurrentHashMap<>();

    @Override
    public String get(String key, String defaultVal) {
        return getProperties().getProperty(key, defaultVal);
    }

    public Properties getProperties(){

        Properties properties = cache.get(ThreadData.get(Keys.USER_LANGUAGE));
        if(properties != null){
            return properties;
        }

        properties = new Properties();
        try(InputStream is =
                    ClassPathMsgSource.class
                            .getClassLoader()
                            .getResourceAsStream(String.format("msg/msg_%s.properties", ThreadData.get(Keys.USER_LANGUAGE).toString()))) {
            if(is != null) {
                properties.load(is);
            }
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
        cache.put(ThreadData.get(Keys.USER_LANGUAGE), properties);
        return properties;
    }
}
