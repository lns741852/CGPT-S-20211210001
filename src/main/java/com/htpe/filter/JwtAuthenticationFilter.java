package com.htpe.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.htpe.utils.JwtUtils;


public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
	    super(authenticationManager);
	}


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try {
        	String token = httpServletRequest.getHeader(JwtUtils.HEADER_STRING);
            if (StringUtils.hasText(token)) {
                Map<String, Object> claims = JwtUtils.validateTokenAndGetClaims(httpServletRequest);
                String role = String.valueOf(claims.get("auth")); 
                String userid = String.valueOf(claims.get("userid"));
                List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList(role);	//封裝權限
                //最關鍵的部分就是這裡, 我們直接注入了
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                        userid, null, auths
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
            httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);	//調用service層

    }

    //是否是保護連線
//    private boolean isProtectedUrl(HttpServletRequest request) {
//
//        boolean flag = false;
//        for (int i = 0; i < protectUrlPattern.length; i++) {
//            if (pathmatcher.match(protectUrlPattern[i], request.getServletPath())) {
//                return true;
//            }
//        }
//        return false;
//    }
}