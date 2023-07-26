package com.huyu.utils;

import com.huyu.common.JwtConstant;
import com.huyu.entity.CheckResult;
import io.jsonwebtoken.*;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

public class JwtUtils {

    /**
     * 签发JWT
     * @param id 使用username作为ID
     * @param subject username
     * @param ttlMillis 过期时间
     * @return token数据
     */
    public static String createJWT(String id, String subject, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);
        SecretKey secretKey = generalKey();
        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)   // 主题
                .setIssuer("huyu")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey); // 签名算法以及密匙
        if (ttlMillis >= 0) {
            long expMillis = currentTimeMillis + ttlMillis;
            Date expDate = new Date(expMillis);
            builder.setExpiration(expDate); // 过期时间
        }
        return builder.compact();
    }

    /**
     * 生成JWT令牌
     * @param username 用户名
     * @return JWT令牌
     */
    public String generateToken(String username) {
        //获取密钥
        SecretKey secretKey = generalKey();
        return Jwts.builder() // 创建JWT生成器对象
                .setId(username) // 设置Id，此处沿用username
                .setSubject(username) // 设置主题，使用用户名
                .setIssuedAt(new Date(System.currentTimeMillis())) // 设置JWT令牌的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 设置JWT令牌的过期时间，这里设置为10个小时
                .signWith(SignatureAlgorithm.HS512, secretKey) // 使用HS512算法和密钥对JWT令牌进行签名
                .compact(); // 生成JWT令牌
    }


    /**
     * 生成jwt token
     * @param username
     * @return
     */
    public static String genJwtToken(String username){
        /*return createJWT(username,username,60*60*1000);*/
        //业务需要设置TOKEN过期时间为一个极大值
        return createJWT(username,username,Long.MAX_VALUE);
    }

    /**
     * 验证JWT
     * @param jwtStr
     * @return
     */
    public static CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        Claims claims = null;
        try {
            claims = parseJWT(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(JwtConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 生成加密Key
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.decode(JwtConstant.JWT_SECERT);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析JWT字符串
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) {
        System.out.println("-----JWTUtils-parseJWT");
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws InterruptedException {
        //小明失效 10s
        String sc = createJWT("1","小明", 60 * 60 * 1000);
        System.out.println(sc);
        System.out.println(validateJWT(sc).getErrCode());
        System.out.println(validateJWT(sc).getClaims().getId());
        System.out.println(validateJWT(sc).getClaims().getSubject());
        //Thread.sleep(3000);
        System.out.println(validateJWT(sc).getClaims());
        Claims claims = validateJWT(sc).getClaims();
        String sc2 = createJWT(claims.getId(),claims.getSubject(), JwtConstant.JWT_TTL);
        System.out.println(sc2);
    }

}
