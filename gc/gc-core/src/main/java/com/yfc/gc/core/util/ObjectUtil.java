package com.yfc.gc.core.util;

import com.yfc.gc.core.exception.SystemExeption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class ObjectUtil extends StringUtils{

    static final char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
    static final char[] vunit = { '万', '亿' }; // 段名表示
    static final char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 为空的
     * @param obj
     * @return
     */
    public static boolean empty(Object obj) {
        if (obj == null){
            return true;
        }
        if (obj instanceof Number){
            return BigDecimal.valueOf(((Number)obj).doubleValue()).compareTo(BigDecimal.valueOf(0.0)) == 0;
        }
        if (obj instanceof CharSequence){
            return StringUtils.isBlank(obj.toString());
        }
        if (obj.getClass().isArray()){
            return Array.getLength(obj)==0;
        }
        if (obj instanceof Collection){
            return CollectionUtils.isEmpty((Collection)obj);
        }
        if (obj instanceof Map){
            return ((Map)obj).isEmpty();
        }
        if(obj instanceof Empty){
            return ((Empty) obj).empty();
        }
        if (Objects.equals("{}", JSONUtil.toJson(obj))){
            return true;
        }
        return false;
    }

    /**
     * 不为空的
     * @param obj
     * @return
     */
    public static boolean notEmpty(Object obj){
        return !empty(obj);
    }

    public static boolean equals(Object arg1, Object arg2){
        return ObjectUtils.nullSafeEquals(arg1, arg2);
    }

    /**
     *  @author: Cl
     *  @Date: 2019/3/25 23:34
     *  @Description: BigDecimal是否相等
     */
    public static boolean equalsBigDecimal(BigDecimal arg1, BigDecimal arg2){
        if (empty(arg1)) {
            arg1 =  new BigDecimal(0);
        }
        if (empty(arg2)) {
            arg2 =  new BigDecimal(0);
        }
        return arg1.compareTo(arg2) == 0;
    }

    public static int hashCode(Object obj){
        return ObjectUtils.nullSafeHashCode(obj);
    }

    public static boolean like(String source, String str){
        return source.indexOf(str) >= 0;
    }

    public static String toString(Object obj){
        return Objects.toString(obj, "");
    }

    public static String toString(byte[] bytes){
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("转换字符串失败:'{}'", bytesToHexStr(bytes));
            return "";
        }
    }

    /**
     * 人民币数字转大写
     * @param value
     * @return
     */
    public static String numberToBig(double value) {
        long midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串

        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix = ""; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0'] + "角"
                    + digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
        char zero = '0'; // 标志'0'表示出现过0
        byte zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0)
                prefix += hunit[idx - 1];
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0)
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        return prefix + suffix; // 返回正确表示
    }

    public static String bytesToHexStr(byte[] bytes){
        if(empty(bytes)){
            return "";
        }
        StringBuilder resultSb = new StringBuilder();
        for (byte info : bytes) {
            resultSb.append(byteToHexStr(info));
        }
        return resultSb.toString();
    }

    public static String bytesToAsciiStr(byte[] bytes){
        if(empty(bytes)){
            return "";
        }
        StringBuilder resultSb = new StringBuilder();
        for (byte info : bytes) {
            resultSb.append(byteToHexStr(info));
        }
        return resultSb.toString();
    }

    /**
     * 转换byte到16进制
     * @param b 要转换的byte
     * @return 16进制格式
     */
    private static String byteToHexStr(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static byte[] hexStrToBytes(String hexStr){
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0;i < bytes.length;i++){
            int strIndex = i * 2;
            int d1 = getIndex(hexStr.charAt(strIndex));
            int d2 = getIndex(hexStr.charAt(strIndex + 1));
            bytes[i] = (byte) (d1 * 16 + d2);
        }
        return bytes;
    }

    private static int getIndex(char c){
        for(int i = 0;i < hexDigits.length;i++){
            if(hexDigits[i].charAt(0) == c){
                return i;
            }
        }
        throw new SystemExeption("不存在的字符串！");
    }

    public static Map<String, Object> toMap(Object obj) {
        return JSONUtil.toObj(JSONUtil.toJson(obj), Map.class);
    }

    public static Map<String, String> toMap(HttpServletRequest request){
        Map<String, String[]> params = request.getParameterMap();
        Map<String, String> res = new HashMap<>();
        params.entrySet()
                .stream()
                .forEach(entry->res.put(entry.getKey(), String.join(",", entry.getValue())));
        return res;
    }

    public static byte[] toBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("转换bytes失败:'{}'", s);
            return new byte[0];
        }
    }
}
