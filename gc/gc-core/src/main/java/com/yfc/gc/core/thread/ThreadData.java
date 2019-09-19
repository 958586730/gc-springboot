package com.yfc.gc.core.thread;


import com.yfc.gc.core.util.ObjectUtil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ThreadData {

    private ThreadData(){}

    private static final ThreadLocal<Map<Keys, Object>> THREAD_DATA = new ThreadLocal<>();

    /**
     * 添加临时数据
     * @Title: set
     * @Description: TODO[]
     * @param @param key
     * @param @param val    设定文件
     * @return void    返回类型
     * @throws
     */
    public static <T> void set(Keys<T> key, T val){
        if(THREAD_DATA.get() == null){
            init();
            set(key, val);
        }else{
            THREAD_DATA.get().put(key, val);
        }
    }

    /**
     * 初始化储存结构
     * @Title: init
     * @Description: TODO[]
     * @return void    返回类型
     * @throws
     */
    private static void init(){
        THREAD_DATA.set(new LinkedHashMap<>());
    }

    /**
     * 获得val
     * @Title: get
     * @param @param key
     * @param @param clazz
     * @param @param defaultValue
     * @param @return    设定文件
     * @return T    返回类型
     * @throws
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(Keys<T> key){
        if(null != THREAD_DATA.get()){
            T result = (T) THREAD_DATA.get().get(key);
            return ObjectUtil.empty(result) ? key.getDefaultValue() : result;
        }else{
            return key.getDefaultValue();
        }
    }

    /**
     * 克隆线程数据
     * @return
     */
    public static Map<Keys, Object> cloneData(){
        if(THREAD_DATA.get() == null){
            return new HashMap<>();
        }
        return new HashMap<>(THREAD_DATA.get());
    }

    public static void clear(){
        if(THREAD_DATA.get() != null){
            THREAD_DATA.get().clear();
        }
    }
}
