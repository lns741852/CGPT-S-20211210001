package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.AccountService;
import com.htpe.service.SetnoService;
import com.htpe.service.impl.AccountServiceImpl;
import com.htpe.utils.ResultMsg;


@RestController
public class CommonController {
	
	@Autowired
	SetnoService setnoService;

	/**
	 * 盤包查詢
	 */
	@GetMapping("/setdata/{setno}")
	public ResultMsg getSetdata(@PathVariable String setno){		
		return setnoService.getSetnoByNo(setno);
	}
	
	/**
	 * 盤包查詢
	 */
	@GetMapping("/setdata/all")
	public ResultMsg getSetdataAll(){		
		return setnoService.getSetnoAll();
	}
	
	

}
