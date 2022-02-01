package com.htpe.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * �ʸ�JSON��^�榡
 */
public abstract class JsonAuthenticationUtils {

	protected void WriteJSON(
				HttpServletRequest request,
				HttpServletResponse response,
				Object data)throws IOException, ServletException {
		//�O��JSON�s�X���T
		response.setContentType("text/json;charset=utf-8");
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Method", "POST,GET");
		//JSON��X
		PrintWriter out = response.getWriter();
		out.write(new ObjectMapper().writeValueAsString(data));
		out.flush();
		out.close();
	}
}
