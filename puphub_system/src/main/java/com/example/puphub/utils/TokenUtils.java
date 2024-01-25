package com.example.puphub.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    public static String genToken(Integer uId, String uPassword){
        return JWT.create().withAudience(uId.toString()) // 将 user id 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),6)) //6小时后token过期
                .sign(Algorithm.HMAC256(uPassword)); // 以 password 作为 token 的密钥
    }
}
