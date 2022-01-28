package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.htpe.utils.ResultMsg;

/**
 * �n�J���\��^JSON
 */
@Component
public class MyAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler{


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {			
			//����ýX
			//response.setContentType("text/json;charset=utf-8");
			//springboot���N���D�|��json
			//response.getWriter().write(new ObjectMapper().writeValueAsString(ResultMsg.success().addMap("msg", "�n�J���\")));
			this.WriteJSON(request, response, ResultMsg.success("�n�J���\").addData(""));
	}

}
