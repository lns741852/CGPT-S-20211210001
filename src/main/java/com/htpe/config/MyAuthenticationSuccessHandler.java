package com.htpe.config;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.htpe.bean.CsrAccount;
import com.htpe.mapper.nnew.CsrAccountMapper;
import com.htpe.utils.JsonAuthenticationUtils;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

/**
 * 登入成功返回JSON
 */
@Component
public class MyAuthenticationSuccessHandler extends JsonAuthenticationUtils implements AuthenticationSuccessHandler{

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	CsrAccountMapper csrAccountMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {	
		//將collection 轉 steam 轉 string 在用","隔開
			String authString = authentication.getAuthorities().stream().map(x -> x.toString()).collect(Collectors.joining(", "));
			CsrAccount csrAccount = csrAccountMapper.getAccountByUserno(authentication.getName());

			String jwt = JwtUtils.generateToken(authString,authentication.getName(), csrAccount.getUsercname());
			response.setHeader(JwtUtils.HEADER_STRING, jwt);
			//防止亂碼
			//response.setContentType("text/json;charset=utf-8");
			//springboot中會將類轉成json
			//response.getWriter().write(new ObjectMapper().writeValueAsString(ResultMsg.success().addMap("msg", "登入成功")));
			this.WriteJSON(request, response, ResultMsg.success("登入成功").addData(""));
	}

}
