package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.htpe.utils.JsonAuthenticationUtils;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

/**
 * 成功後返回JSON
 */
@Component
public class MyLogoutSuccessHandler extends JsonAuthenticationUtils implements LogoutSuccessHandler {


	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if(authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}

		response.setHeader(JwtUtils.HEADER_STRING, "");
		this.WriteJSON(request, response, ResultMsg.success("登出成功").addData(""));
		
	}
	

}
