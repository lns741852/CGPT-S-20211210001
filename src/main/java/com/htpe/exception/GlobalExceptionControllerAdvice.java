package com.htpe.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.htpe.utils.ResultMsg;

import lombok.extern.slf4j.Slf4j;


/**
 * jsr303共用配置
 *
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.htpe.controller")		
public class GlobalExceptionControllerAdvice {

	@ExceptionHandler(value= {MethodArgumentNotValidException.class , BindException.class})
    public ResultMsg handleVaildException(Exception e){
        BindingResult bindingResult = null;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException)e).getBindingResult();
        } else if (e instanceof BindException) {
            bindingResult = ((BindException)e).getBindingResult();
        }
        Map<String,String> errorMap = new HashMap<>(16);
        bindingResult.getFieldErrors().forEach((fieldError)->
                errorMap.put(fieldError.getField(),fieldError.getDefaultMessage())
        );
        
        for(String msg : errorMap.values()) {
        	return ResultMsg.fail(401, msg).addData("");		//自定義類
        }

        return ResultMsg.fail(500, "GlobalExceptionControllerAdvice程式異常").addData("");
    }

}