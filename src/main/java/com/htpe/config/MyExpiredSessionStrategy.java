package com.htpe.config;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import com.htpe.utils.JsonAuthenticationUtils;
import com.htpe.utils.ResultMsg;


/**
 * �H�ƹL�h��U�u���^JSON
 */
@Component
public class MyExpiredSessionStrategy extends JsonAuthenticationUtils implements SessionInformationExpiredStrategy{

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		this.WriteJSON(event.getRequest(), event.getResponse(), ResultMsg.fail(403,"�Τ�Ӧh").addData(""));
		
	}

}
