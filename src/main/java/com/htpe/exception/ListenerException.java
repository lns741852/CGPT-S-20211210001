package com.htpe.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.htpe.utils.ResultMsg;

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
		return ResultMsg.fail(401,split[5]+"格式錯誤").addData("");
	}

	//request流程異常
    @ExceptionHandler(value = RequestPeriodException.class)
    public ResultMsg requesPeriodException(RequestPeriodException e) {
    	return ResultMsg.fail(e.getCode(),e.getMsg()).addData("");
    }
}
