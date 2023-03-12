package com.example.sexEducationForChildren.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * jwt工具类
 */
public class JWTUtil {
    private static final long EXPIRE_TIME = 60 * 60 * 1000;//默认1小时
//    private static final long EXPIRE_TIME = 3000;//默认1小时
    //私钥
    private static final String TOKEN_SECRET = "privateKey";

    /**
     * 生成签名，1小时过期
     * @param **username**
     * @param **password**
     * @return
     */
    public static String createToken(Integer userId) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withClaim("createDate",new Date().getTime())
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 生成token，自定义过期时间 毫秒
     * @param **username**
     * @param **password**
     * @return
     */
    public static String createToken(String userId,long expireDate) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + expireDate);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检验token是否正确
     * @param **token**
     * @return
     */
    public static Integer verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Integer userId = jwt.getClaim("userId").asInt();
            return userId;
        } catch (Exception e){
            return null;
        }
    }

    /**
     * 判断token是否过期
     * @param **token**
     * @return
     */
    public static boolean overTime(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            Long date = jwt.getClaim("createDate").asLong();
            Integer userId = jwt.getClaim("userId").asInt();
            System.out.println(userId);
            System.out.println(date);
            if (new Date().getTime() - date > EXPIRE_TIME) {
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }


    public static void main(String[] args) {
//        String token = JWTUtil.createToken("zhaohy", 3000);
        String token = JWTUtil.createToken(2);
//        String token = "eyJUeXBlIjoiSnd0IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE2NzEzNjkyMjcsImNyZWF0ZURhdGUiOjE2NzEzNjU2MjcyNDB9.5s4bzQ_Tusrscf8bGJscPISBLR_ys-fFDTpe46mADu8";
        System.out.println("token == " + token);
        Integer userId = JWTUtil.verifyToken(token);
        System.out.println(JWTUtil.overTime(token));
        System.out.println("userId == " + userId);

//        //新建定时任务
//        Runnable runnable = new Runnable() {
//            //run方法中是定时执行的操作
//            public void run() {
//                System.out.println(new Date());
//                String userId = JWTUtil.verifyToken(token);
//                System.out.println("userId == " + userId);
//            }
//        };
//        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
//        /*
//         * 参数一:command：执行线程
//         * 参数二:initialDelay：初始化延时
//         * 参数三:period：两次开始执行最小间隔时间
//         * 参数四:unit：计时单位
//         */
//        service.scheduleAtFixedRate(runnable, 0, 4, TimeUnit.SECONDS);
    }
}
