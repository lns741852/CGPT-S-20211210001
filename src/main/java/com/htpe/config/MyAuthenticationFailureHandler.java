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
 * �n�J���Ѫ�^JSON
 */
@Component
public class MyAuthenticationFailureHandler extends JSONAuthentication implements AuthenticationFailureHandler{


	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//����ýX
		//response.setContentType("text/json;charset=utf-8");
		//springboot���N���D�|��json
		//response.getWriter().write(new ObjectMapper().writeValueAsString(ResultMsg.fail().addMap("msg", "�n�J����")));
		
		//�~��JSONAuthentication
		this.WriteJSON(request, response, ResultMsg.fail(401,"�n�J����").addData(""));
		
	}

}
