package top.hting.cloud.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * jwt工具，生成，解析token
 */
public class JWTUtils {

    public static final String JWT_USER_ID = "userId";
    public static final String JWT_USER_NAME = "userName";

    /**
     * 私钥
     */
    private static String secret = "123456";



    /**
     * 生成token，默认30分钟
     * @param userInfo
     * @return
     */
    public static String generateToken(JWTUserInfo userInfo) {
        return generateToken(userInfo, 30 );
    }

    public static String generateToken(JWTUserInfo userInfo, int expireMinute){

        return Jwts.builder()
                .setSubject(userInfo.getAccount())
                .claim(JWT_USER_ID, userInfo.getUserId())
                .claim(JWT_USER_NAME, userInfo.getUserName())
                .setExpiration(generateExpirationDate(expireMinute))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

    }

    /**
     * 解析token，返回用户信息
     *
     * @param token
     * @return
     */
    public static JWTUserInfo getInfoFromToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

        Claims body = claimsJws.getBody();

        return new JWTUserInfo(body.getSubject(),
                body.get(JWT_USER_NAME) == null?"":body.get(JWT_USER_NAME).toString(),
                body.get(JWT_USER_ID)==null?"":body.get(JWT_USER_ID).toString());
    }

    /**
     * 验证token是否过期
     * @return true过期
     */
    public static boolean isExpire(String token){

        Claims body = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return body.getExpiration().before(new Date());

    }



    /**
     * 获取之后的时间
     *
     * @param expireMinute 分钟
     * @return
     */
    private static Date generateExpirationDate(int expireMinute){
        return new Date(System.currentTimeMillis() + expireMinute * 60 * 1000);
    }

}
