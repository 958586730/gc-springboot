package com.yfc.gc.bean.sys.webchat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WechatAuthCodeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openid;

    @JsonProperty("session_key")
    private String sessionKey;

    /**
     * 请求失败错误码
     */
    private String errcode;

    /**
     * 请求失败错误信息
     */
    private String errmsg;

    /**
     * 会话有效期（以前微信会返回，现在未知）
     */
    @JsonProperty("expires_in")
    private Long expiresIn;

    /**
     * 包括敏感数据在内的完整用户信息的加密数据
     */
    private String encryptedData;

    /**
     * 加密算法的初始向量
     */
    private String iv;

    @Override
    public String toString() {
        return "WechatAuthCodeResponse{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", errcode='" + errcode + '\'' +
                ", errmsg='" + errmsg + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }

}
