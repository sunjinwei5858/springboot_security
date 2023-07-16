package com.sunjinwei.springboot_security.util;

import com.sunjinwei.springboot_security.domain.UserInfo;
import io.jsonwebtoken.*;

import java.util.Date;
import java.util.UUID;

/**
 * @program: com.sunjinwei.springboot_jwt.util
 * @author: sun jinwei
 * @create: 2023-07-15 21:51
 * @description:
 **/
public class JwtUtil {

    public static String SIGN_KEY = "admin";

    // token过期时间
    public static long EXPIRE_MILLIS = 1000 * 60 * 60;

    public static String JWT_ACCOUNT_CLAIM_KEY = "account";


    public static String createJwt(UserInfo userInfo) {
        JwtBuilder builder = Jwts.builder();
        return builder
                // 1 Header 头部
                .setHeaderParam("typ", "jwt")
                .setHeaderParam("alg", "HS256")
                // 2 Payload 载荷
                .claim(JWT_ACCOUNT_CLAIM_KEY, userInfo)
                // token 过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_MILLIS))
                // id字段
                .setId(UUID.randomUUID().toString())
                // sign 签名: 设置加密算法+签名
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)
                // 使用 . 连接成一个完整的字符串
                .compact();
    }

    public static boolean checkJwt(String jwtToken) {

        return Jwts.parser().isSigned(jwtToken);

    }

    public static UserInfo parseJwt(String jwtToken) {
        // 1 获取jwt解析对象
        JwtParser parser = Jwts.parser();
        // 2 将jwtToken转化为一个key-value 通过key来获取
        Jws<Claims> claimsJws = parser.setSigningKey(SIGN_KEY).parseClaimsJws(jwtToken);
        // 3 获取Jwt对象中的数据，get(key)表示根据key来获取value
        return (UserInfo) claimsJws.getBody().get(JWT_ACCOUNT_CLAIM_KEY);
    }
}