package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.htpe.utils.JsonAuthenticationUtils;
import com.htpe.utils.ResultMsg;

/**
 * 無權限返回JSON
 */
@Component("MyAuthenticationEntryPoint")
public class MyAuthenticationEntryPoint extends JsonAuthenticationUtils implements AuthenticationEntryPoint{


	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		this.WriteJSON(request, response, ResultMsg.fail(403,"沒有權限").addData(""));
		
	}

}
