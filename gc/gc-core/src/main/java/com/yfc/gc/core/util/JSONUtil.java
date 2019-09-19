package com.yfc.gc.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;

@Slf4j
public class JSONUtil {

    public static String toJson(Object obj, SerializerFeature... features){
        try{
            return JSON.toJSONString(obj, features);
        }catch (Exception e) {
            log.error("参数:{}", obj);
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> List<T> toList(String s, Class<T> c){
        try {
            return JSON.parseArray(s, c);
        } catch (Exception e) {
            log.error("参数:{}, 类型:{}", s, c);
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T>T toObj(String s, Class<T> c, Feature... features){
        try {
            return JSON.parseObject(s, c, features);
        } catch (Exception e) {
            log.error("参数:{}, 类型:{}", s, c);
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T>T toObj(String json, Type type, Feature... features){
        try{
            return JSON.parseObject(json, type, features);
        } catch (Exception e) {
            log.error("参数:{}, 类型:{}", json, type);
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
