package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.htpe.utils.ResultMsg;

/**
 * 成功後返回JSON
 */
@Component
public class MyLogoutSuccessHandler extends JSONAuthentication implements LogoutSuccessHandler {


	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		this.WriteJSON(request, response, ResultMsg.success("登出成功").addData(""));
		
	}
	

}
