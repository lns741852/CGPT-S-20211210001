package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.ApplyService;
import com.htpe.utils.ResultMsg;

@RestController
public class ApplyController {
	
	@Autowired
	ApplyService applyService;
	
	/**
	 * 成本中心查詢
	 */
	@GetMapping("/costcenter")
	public ResultMsg  listCostcenter() {
	    return applyService.listCostcenter();	  
	}
	

}
