package org.mantou.ad.utils;

import io.jsonwebtoken.*;
import org.mantou.ad.enums.ResultEnum;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.Map;

/**
 * API调用认证工具类，采用RSA加密
 */
public class JWTUtils {
    private static RSAPrivateKey priKey;
    private static RSAPublicKey pubKey;

    public synchronized static JWTUtils getInstance(String modulus, String privateExponent, String publicExponent) {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
            pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
        }
        return SingletonHolder.INSTANCE;
    }

    public synchronized static JWTUtils getInstance() {
        if (priKey == null && pubKey == null) {
            priKey = RSAUtils.getPrivateKey(RSAUtils.modulus, RSAUtils.private_exponent);
            pubKey = RSAUtils.getPublicKey(RSAUtils.modulus, RSAUtils.public_exponent);
        }
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取Token
     *
     * @param id 用户id
     * @param exp 失效时间，单位分钟
     * @return
     */
    public static String getToken(String id, Map<String, Object> claims, int exp) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * exp;
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(id)
                .setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey);
        claims.forEach((k,v) -> {
                    jwtBuilder.claim(k, v);
                });
        return jwtBuilder.compact();
    }

    public synchronized static void reload(String modulus, String privateExponent, String publicExponent) {
        priKey = RSAUtils.getPrivateKey(modulus, privateExponent);
        pubKey = RSAUtils.getPublicKey(modulus, publicExponent);
    }

    /**
     * 检查Token是否合法
     *
     * @param token
     * @return JWTResult
     */
    public JWTResult checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(pubKey).parseClaimsJws(token).getBody();
            String id = claims.getId();
            return new JWTResult(true, claims, "合法请求", ResultEnum.SUCCESS_STATUS.getStatus());
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return new JWTResult(false, null, "token已过期", ResultEnum.TOKEN_TIMEOUT_CODE.getStatus());
        } catch (SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            return new JWTResult(false, null, "非法请求", ResultEnum.NO_AUTH_CODE.getStatus());
        } catch (Exception e) {
            return new JWTResult(false, null, "非法请求", ResultEnum.NO_AUTH_CODE.getStatus());
        }
    }

    /**
     * 获取Token
     * @param id 用户ID
     * @return
     */
    public String getToken(String id, Map<String, Object> claims) {
        long endTime = System.currentTimeMillis() + 1000 * 60 * 1440;
        JwtBuilder jwtBuilder = Jwts.builder().setId(id)
                .setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, priKey);
        claims.forEach((k,v) ->{
            jwtBuilder.claim(k, v);
        });
        return jwtBuilder.compact();
    }

    public static class JWTResult {
        private String msg;
        private boolean res;
        private int status;
        private Object uid;

        public JWTResult() {
            super();
        }

        public JWTResult(boolean res, Object uid, String msg, int status) {
            super();
            this.status = status;
            this.uid = uid;
            this.msg = msg;
            this.res = res;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public Object getUid() {
            return uid;
        }

        public void setUid(Object uid) {
            this.uid = uid;
        }

        public boolean isRes() {
            return res;
        }

        public void setRes(boolean res) {
            this.res = res;
        }
    }

    private static class SingletonHolder {
        private static final JWTUtils INSTANCE = new JWTUtils();
    }

}
