package com.yfc.gc.core.password;

import com.yfc.gc.core.consts.SystemConst;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/*
 * MD5 算法
 */
@Slf4j
public class MD5 implements SystemConst {

    // 全局数组
    private static final String[] strDigits = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    private MD5(){

    }

    // 返回形式为数字跟字符串
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    @SuppressWarnings("unused")
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    // 转换字节数组为16进制字串
    private static String byteToString(byte[] bByte) {
        StringBuilder sBuffer = new StringBuilder();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String toMD5(String strObj) {
        if(strObj == null){
            return null;
        }
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteToString(md.digest(strObj.getBytes(PROJECT_CHARSET)));
            log.debug("MD5前'{}',后'{}'", strObj, resultString);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return resultString;
    }
}