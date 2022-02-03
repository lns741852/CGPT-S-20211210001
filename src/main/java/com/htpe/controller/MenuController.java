package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.MenuService;
import com.htpe.utils.ResultMsg;

@RestController
public class MenuController {
	
	@Autowired
	MenuService menuService;
	
	@GetMapping("/menu")
	public ResultMsg getMenuByUsername(HttpServletRequest request){		
		return menuService.getMenuByUsername(request);
	}

}
