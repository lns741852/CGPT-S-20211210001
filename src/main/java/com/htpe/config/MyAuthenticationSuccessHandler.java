package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.htpe.bean.CsrAccount;
import com.htpe.mapper.nnew.CsrAccountMapper;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

/**
 * �n�J���\��^JSON
 */
@Component
public class MyAuthenticationSuccessHandler extends JSONAuthentication implements AuthenticationSuccessHandler{

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	CsrAccountMapper csrAccountMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
				Authentication authentication) throws IOException, ServletException {		
			CsrAccount csrAccount = csrAccountMapper.getAccountByUserno(authentication.getName());
			String jwt = JwtUtils.generateToken(csrAccount.getSystemprivilege(),authentication.getName());
			response.setHeader(JwtUtils.HEADER_STRING, jwt);
			//����ýX
			//response.setContentType("text/json;charset=utf-8");
			//springboot���|�N���নjson
			//response.getWriter().write(new ObjectMapper().writeValueAsString(ResultMsg.success().addMap("msg", "�n�J���\")));
			this.WriteJSON(request, response, ResultMsg.success("�n�J���\").addData(""));
	}

}
