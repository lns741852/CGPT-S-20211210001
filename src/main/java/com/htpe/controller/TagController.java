package com.htpe.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.DepnoService;
import com.htpe.service.SetnoService;
import com.htpe.utils.ResultMsg;



@RestController
public class TagController {
	
	@Autowired
	SetnoService service;
	
	@Autowired
	DepnoService depnoService;
	
	/**
	 * 盤包單一查詢
	 */
	@GetMapping("/setno_name/{name}")
	public ResultMsg getSetno(@PathVariable String name){			
		return service.getSetnoByName(name);
	}
	
	/**
	 * 部門單一查詢
	 */
	@GetMapping("/depno_name/{name}")
	public ResultMsg getdepno(@PathVariable String name){			
		return depnoService.getDepnoByName(name);
	}
	
	
	

}
