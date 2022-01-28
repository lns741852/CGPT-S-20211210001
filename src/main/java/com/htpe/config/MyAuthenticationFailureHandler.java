package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.htpe.utils.ResultMsg;

/**
 * 登入失敗返回JSON
 */
@Component
public class MyAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler{


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//防止亂碼
		//response.setContentType("text/json;charset=utf-8");
		//springboot中將類沝會成json
		//response.getWriter().write(new ObjectMapper().writeValueAsString(ResultMsg.fail().addMap("msg", "登入失敗")));
		
		//繼承JSONAuthentication
		this.WriteJSON(request, response, ResultMsg.fail(401,"登入失敗").addData(""));
		
	}

}
