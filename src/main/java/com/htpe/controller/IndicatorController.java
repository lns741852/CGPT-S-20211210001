package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrPoltld;
import com.htpe.service.IndicatorService;
import com.htpe.utils.ResultMsg;

@RestController
public class IndicatorController {
	
	@Autowired
	IndicatorService indicatorService;
	
	/**
	 *滅菌鍋監控查詢
	 */
	@PostMapping("/indicator")
	public ResultMsg  getIndicatorByAll(@RequestBody CsrPoltld csrPoltld) {
	    return  indicatorService.getIndicatorByAll(csrPoltld);
	}

}
