package com.htpe.utils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 封裝JSON返回格式
 */
public abstract class JsonAuthenticationUtils {

	protected void WriteJSON(
				HttpServletRequest request,
				HttpServletResponse response,
				Object data)throws IOException, ServletException {
		//保證JSON編碼正確
		response.setContentType("text/json;charset=utf-8");
		//response.setHeader("Access-Control-Allow-Origin", "*");
		//response.setHeader("Access-Control-Allow-Method", "POST,GET");
		//JSON輸出
		PrintWriter out = response.getWriter();
		out.write(new ObjectMapper().writeValueAsString(data));
		out.flush();
		out.close();
	}
}
