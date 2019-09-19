package com.yfc.gc.core.util.msg;

public interface MsgSource {

    default String get(String key){
        return get(key, "");
    }

    String get(String key, String defaultVal);
}
