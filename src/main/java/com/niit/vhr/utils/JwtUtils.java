package com.niit.vhr.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;

/**
 * @author : zhayh
 * @date : 2020-5-9 09:37
 * @description :
 */
public class JwtUtils {
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    // 过期时间是3600秒，1个小时
    public static final long EXPIRATION = 5 * 60L;
    public static final String ROLE_CLAIMS = "authorities";

    private static final String SECRET = "vhrJwtSecret";
    private static final String ISS = "echisan";

    // 选择了记住我之后的过期时间为7天
    private static final long EXPIRATION_REMEMBER = 7 * 24 * 60 * 60L;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 创建token
    public static String createToken(String username, String authority, boolean isRememberMe) {
        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;
        HashMap<String, Object> map = new HashMap<>();
        map.put(ROLE_CLAIMS, authority);
        return Jwts.builder()
                .setClaims(map)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();

    }

    // 从token中获取用户名
    public static String getAuthorities(String token) {
        return (String) getTokenBody(token).get(ROLE_CLAIMS);
    }

    // 从token中获取用户名
    public static String getUsername(String token) {
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public static boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}
