package com.yfc.gc.core.password;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

@Slf4j
public class Des {

    private Des() {}

    /** Base64 编码 */
    private static final Base64 B64 = new Base64();
    /** 安全的随机数源 */
    private static final SecureRandom RANDOM = new SecureRandom();
    /** SHA-1加密 */
    private static MessageDigest SHA_1 = null;

    static {
        init();
    }

    /** 初始化 */
    private static void init() {
        try {
            SHA_1 = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /** DES加密算法 */
    private static final String DES_ALGORITHM = "DESede"; // 可用 DES,DESede,Blowfish
    /** DES默认加密 */
    private static Cipher DES_CIPHER_ENC = null;
    /** DES默认解密 */
    private static Cipher DES_CIPHER_DEC = null;

    static {
        // 添加JCE算法
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        // 初始化默认DES加密
        try {
            // 密钥
            SecretKey desKey = new SecretKeySpec(new byte[] { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74,
                    (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 }, DES_ALGORITHM);
            // 初始化默认加密
            DES_CIPHER_ENC = Cipher.getInstance(DES_ALGORITHM);
            DES_CIPHER_ENC.init(Cipher.ENCRYPT_MODE, desKey, RANDOM);
            // 初始化默认解密
            DES_CIPHER_DEC = Cipher.getInstance(DES_ALGORITHM);
            DES_CIPHER_DEC.init(Cipher.DECRYPT_MODE, desKey, RANDOM);
        } catch (Exception e) {
            log.error("DES默认加密解密初始化失败：" + e.getMessage());
        }
    }

    /**
     * DES加密
     *
     * @param str
     *            需要加密的明文
     * @param key
     *            密钥(长度小于24字节自动补足，大于24取前24字节)
     * @return 加密后的密文(base64编码字符串)
     */
    public static String desEncryp(String str, String key) throws Exception {
        return desEncryp(str, key, false);
    }

    /**
     * DES加密
     *
     * @param str
     *            需要加密的明文
     * @param key
     *            密钥(长度小于24字节自动补足，大于24取前24字节)
     * @param urlSafety
     *            密文是否需要Url安全
     * @return 加密后的密文(str/key为null返回null)
     */
    private static String desEncryp(String str, String key, boolean urlSafety) throws Exception {
        Cipher c = Cipher.getInstance(DES_ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, desKey(key), RANDOM);
        // 加密
        byte[] bytes = c.doFinal(str.getBytes("UTF-8"));// 加密
        // 返回b64处理后的字符串
        if (urlSafety) {
            return Base64.encodeBase64URLSafeString(bytes);
        } else {
            return new String(B64.encode(bytes));
        }
    }

    /**
     * DES解密
     *
     * @param str
     *            需要解密的密文(base64编码字符串)
     * @param key
     *            密钥(长度小于24字节自动补足，大于24取前24字节)
     * @return 解密后的明文
     */
    public static String desDecrypt(String str, String key) throws Exception {//解密
        Cipher c = Cipher.getInstance(DES_ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, desKey(key), RANDOM);
        byte[] bytes = c.doFinal(B64.decode(str));
        return new String(bytes, "UTF-8");
    }

    /** DES密钥 */
    private static SecretKey desKey(String key) {
        byte[] bs = key.getBytes();
        if (bs.length != 24) {
            bs = Arrays.copyOf(bs, 24);// 处理数组长度为24
        }
        return new SecretKeySpec(bs, DES_ALGORITHM);
    }

    public static void main(String... args) throws Exception {
        System.out.println(desDecrypt("A3KsWZwNa+E1nknNGfGBZYqD7m/lZN4BRx9j+rAk74x35tIH0yumvg==", "gc"));
    }
}