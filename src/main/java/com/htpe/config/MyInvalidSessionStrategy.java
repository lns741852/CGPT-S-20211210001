package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.stereotype.Component;

import com.htpe.utils.JsonAuthenticationUtils;
import com.htpe.utils.ResultMsg;

/**
 * Session到期後返回JSON
 */
@Component
public class MyInvalidSessionStrategy extends JsonAuthenticationUtils implements InvalidSessionStrategy{


	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ResultMsg.fail(403,"登入超時").addData("");
		
	}

}
