package com.yfc.gc.core.util.msg;

import com.yfc.gc.core.util.ObjectUtil;

import java.text.MessageFormat;

/**
 * 国际化工具类
 */
public class MsgUtil {

    private static MsgSource msgSource;

    public static String get(String key, Object... args){
        return get(key, "", args);
    }

    public static String get(String key, String defVal, Object... args){
        return ObjectUtil.empty(args) ? getMsgSource().get(key, defVal) : MessageFormat.format(getMsgSource().get(key, defVal), args);
    }

    public static MsgSource getMsgSource(){
        if(msgSource == null){
            msgSource = new ClassPathMsgSource();
        }
        return msgSource;
    }

    public static void setMsgSource(MsgSource msgSource){
        MsgUtil.msgSource = msgSource;
    }
}
