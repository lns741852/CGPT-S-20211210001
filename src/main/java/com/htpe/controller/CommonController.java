package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.SetnoService;
import com.htpe.utils.ResultMsg;


@RestController
public class CommonController {
	
	@Autowired
	SetnoService setnoService;
	
	
	/**
	 * ½L¥]¬d¸ß
	 */
	@GetMapping("/setdata")
	public ResultMsg getSetdata(@RequestParam("setno") String setno){		
		return setnoService.getSetnoByNo(setno);
	}

}
