package com.yfc.gc.core.util.msg;

import com.yfc.gc.core.thread.Keys;
import com.yfc.gc.core.thread.ThreadData;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class FileMsgSource implements MsgSource {

    private String filePath;

    private Map<Locale, Properties> cache = new ConcurrentHashMap<>();

    public FileMsgSource(String filePath){
        this.filePath = filePath;
    }

    @Override
    public String get(String key, String defaultVal) {
        return getProperties().getProperty(key, defaultVal);
    }

    public Properties getProperties(){
        Path path = Paths.get(filePath, String.format("msg/msg_%s.properties", ThreadData.get(Keys.USER_LANGUAGE).toString()));
        Properties properties = cache.get(ThreadData.get(Keys.USER_LANGUAGE));
        if(properties != null){
            return properties;
        }
        File file = path.toFile();
        properties = new Properties();
        if(!file.exists()){
            log.error(path.toString()+" is no exists!");
            return properties;
        }
        try(InputStream is = new FileInputStream(file)) {
            properties.load(is);
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
        cache.put(ThreadData.get(Keys.USER_LANGUAGE), properties);
        return properties;
    }

}
