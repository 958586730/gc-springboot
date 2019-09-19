package com.yfc.gc.core.util.wechat;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;


/**
 * @author: Cl
 * @Date: 2019/5/6 15:30
 * @Description: 微信工具类
 */
@Slf4j
public class WechatDecryptDataUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
    private static Key key;
    private static Cipher cipher;

    public static void main(String[] args) {
        String result = decryptData(
                "CiyLU1Aw2KjvrjMdj8YKliAjtP4gsMZM\n" +
                        "                QmRzooG2xrDcvSnxIMXFufNstNGTyaGS\n" +
                        "                9uT5geRa0W4oTOb1WT7fJlAC+oNPdbB+\n" +
                        "                3hVbJSRgv+4lGOETKUQz6OYStslQ142d\n" +
                        "                NCuabNPGBzlooOmB231qMM85d2/fV6Ch\n" +
                        "                evvXvQP8Hkue1poOFtnEtpyxVLW1zAo6\n" +
                        "                /1Xx1COxFvrc2d7UL/lmHInNlxuacJXw\n" +
                        "                u0fjpXfz/YqYzBIBzD6WUfTIF9GRHpOn\n" +
                        "                /Hz7saL8xz+W//FRAUid1OksQaQx4CMs\n" +
                        "                8LOddcQhULW4ucetDf96JcR3g0gfRK4P\n" +
                        "                C7E/r7Z6xNrXd2UIeorGj5Ef7b1pJAYB\n" +
                        "                6Y5anaHqZ9J6nKEBvB4DnNLIVWSgARns\n" +
                        "                /8wR2SiRS7MNACwTyrGvt9ts8p12PKFd\n" +
                        "                lqYTopNHR1Vf7XjfhQlVsAJdNiKdYmYV\n" +
                        "                oKlaRv85IfVunYzO0IKXsyl7JCUjCpoG\n" +
                        "                20f0a04COwfneQAGGwd5oa+T8yO5hzuy\n" +
                        "                Db/XcxxmK01EpqOyuxINew==",
                "tiihtNczf5v6AKRyjwEUhQ==",
                "r7BXXKkLb8qrSNn05n0qiA=="
        );
        System.out.println("result = " + result);
    }

    /**
     * @author: Cl
     * @Date: 2019/5/6 17:05
     * @Description: 解密敏感数据
     */
    public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
        log.debug("---------解密开始-------");
        log.debug("参数 encryptData:" + encryptDataB64);
        log.debug("参数 iv:" + ivB64);
        log.debug("参数 sessionKey:" + sessionKeyB64);
        String phoneInfo = new String(
                decryptOfDiyIV(
                        Base64.decode(encryptDataB64),
                        Base64.decode(sessionKeyB64),
                        Base64.decode(ivB64)
                )
        );
        log.debug("解密结果 phoneInfo:" + phoneInfo);
        log.debug("---------解密结束-------");
        return phoneInfo;
    }

    /**
     * @author: Cl
     * @Date: 2019/5/6 17:06
     * @Description: 初始化参数
     */
    private static void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @param keyBytes      解密密钥
     * @param ivs           自定义对称解密算法初始向量 iv
     * @return 解密后的字节数组
     */
    private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
        byte[] encryptedText = null;
        init(keyBytes);
        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return encryptedText;
    }

}
