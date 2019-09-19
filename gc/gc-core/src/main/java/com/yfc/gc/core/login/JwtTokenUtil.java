package com.yfc.gc.core.login;


import com.yfc.gc.core.exception.TokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author WangYX
 * @Description //TODO JWT工具类
 * @Date 2018/12/24 下午10:09
 **/
@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

    /**
     * 密钥
     */
    @Value("${jwt.secret:jwt}")
    private String secret;

    @Value("${jwt.expiration:24}")
    private Integer expiration;

    /**
     * 从数据声明生成令牌
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(
                // 有效期为n分钟
                Date.from(LocalDateTime.now().plusMinutes(expiration).atZone(ZoneId.systemDefault()).toInstant())
        ).signWith(SignatureAlgorithm.HS512, TextCodec.BASE64.encode(secret)).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TextCodec.BASE64.encode(secret)).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @param userId 用户id
     * @return 令牌
     */
    public String generateToken(String userId) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put("sub", userId);
        claims.put("created", new Date());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUserIdFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if(claims == null || claims.getExpiration().before(new Date())){
            throw new TokenException("登录状态失效");
        }
        return claims.getSubject();
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

}