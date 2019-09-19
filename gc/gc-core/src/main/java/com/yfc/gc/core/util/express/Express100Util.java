package com.yfc.gc.core.util.express;

import com.yfc.gc.core.util.JSONUtil;
import com.yfc.gc.core.util.http.HttpBuild;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Cl
 * @Date: 2019/4/3 17:27
 * @Description: 快递100共通
 */
@Component
public class Express100Util {

    // 快递100的URL地址
    @Value("${express100.url}")
    private String url;
    // 快递100的key
    @Value("${express100.key}")
    private String key;
    // 快递100的customer
    @Value("${express100.customer}")
    private String customer;

    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static MessageDigest _mdInst = null;

    /**
     * @author: Cl
     * @Date: 2019/4/3 17:24
     * @Description: 生成MessageDigest
     */
    private MessageDigest getMdInst() {
        if (_mdInst == null) {
            try {
                _mdInst = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                _mdInst = null;
            }
        }
        return _mdInst;
    }

    /**
     * @author: Cl
     * @Date: 2019/4/3 17:24
     * @Description: 快递100MD5
     */
    public final String encode(String s) {
        try {
            byte[] btInput = s.getBytes();
            // 使用指定的字节更新摘要
            getMdInst().update(btInput);
            // 获得密文
            byte[] md = getMdInst().digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author: Cl
     * @Date: 2019/4/3 17:26
     * @Description: 获取快递信息
     */
    public Express getExpress(Express e) {
        Map<String, Object> map = new HashMap<>();
        map.put("com", e.getCom());
        map.put("num", e.getNu());
        String sign = encode(JSONUtil.toJson(map) + key + customer);
        return HttpBuild.get(url, Express.class)
                .addUrlParam("customer", customer)
                .addUrlParam("sign", sign)
                .addUrlParam("param", JSONUtil.toJson(map))
                .execute(Express.class)
                .orElse(new Express());
    }
}