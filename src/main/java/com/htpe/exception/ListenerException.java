package com.htpe.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.htpe.utils.ResultMsg;

import io.jsonwebtoken.ExpiredJwtException;

/**
 * 監聽異常
 * @author Administrator
 *
 */

@RestControllerAdvice
public class ListenerException {
	
	//參數類型異常
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResultMsg messageNotReadableException(HttpMessageNotReadableException e){	
		String message = e.getMessage();
		String[] split = message.split("\"");
		return ResultMsg.fail(400,split[5]+"格式錯誤").addData("");
	}

	//request流程異常
    @ExceptionHandler(value = RequestPeriodException.class)
    public ResultMsg requesPeriodException(RequestPeriodException e) {
    	return ResultMsg.fail(e.getCode(),e.getMsg()).addData("");
    }
       
	//JWT異常
    @ExceptionHandler(value = ExpiredJwtException.class)
    public ResultMsg jwtException(ExpiredJwtException e) {
    	return ResultMsg.fail(403,"權限發生異常請重新登入").addData("");
    }
       
}
