package com.htpe.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.htpe.exception.RequestPeriodException;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;




@Component
public class JwtUtils {
    public static  final long EXPIRATION_TIME=60*60*24*1000;// 令牌環有效期
    public static final String SECRET="abccba123321";//令牌環金鑰
    public static final String TOKEN_PREFIX="Bearer";//令牌環頭標識
    public static final String HEADER_STRING="Authorization";//配置令牌環在http heads中的鍵值

    //生成令牌環
    public static String generateToken(String userRole,String userid){
        HashMap<String,Object> map=new HashMap<>();
        map.put("auth",userRole);
        map.put("userid",userid);
        String jwt= Jwts.builder()
        		//.setHeaderParam("typ","JWT");
        		//.setSubject(userid);
        		//.setIsskuedAt(new Date());
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        return TOKEN_PREFIX+" "+jwt;
    }
    //生成令牌環
    public static String generateToken(String userRole,String userid,long exprationtime){
        HashMap<String,Object> map=new HashMap<>();
        map.put("auth",userRole);
        map.put("userid",userid);
        String jwt= Jwts.builder()
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+exprationtime))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
        return TOKEN_PREFIX+" "+jwt;
    }

    //令牌環校驗
    public static Map<String,Object> validateTokenAndGetClaims(HttpServletRequest request) throws RequestPeriodException{
        String token=request.getHeader(HEADER_STRING);
        if(token==null){
            throw new RequestPeriodException(403, "Token沒有值");
        }
        else{
            Map<String,Object> map= Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX,""))
                    .getBody();
            return map;
        }
    }
   
}