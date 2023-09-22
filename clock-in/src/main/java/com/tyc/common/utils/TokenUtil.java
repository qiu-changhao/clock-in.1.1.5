package com.tyc.common.utils;

import com.tyc.common.exception.ServiceException;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 令牌工具类
 * @author 唐溢聪
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Component
public class TokenUtil {

    private static Long expirationTime;

    private static String signKey;

    public static String createToken(Map<String,Object> map){
        long now = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, signKey)
                .setClaims(map)
                //.setExpiration(new Date(now + expirationTime))
                .compact();
    }

    public static Claims getClaims(String token){
        try {
            return Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new ServiceException("令牌已过期");
        } catch (SignatureException e) {
            throw new ServiceException("令牌不合法");
        }
    }

    @Value("${token.expirationTime}")
    public void setExpirationTime(Long expirationTime) {
        TokenUtil.expirationTime = expirationTime;
    }

    @Value("${token.signKey}")
    public void setSignKey(String signKey) {
        TokenUtil.signKey = signKey;
    }
}
