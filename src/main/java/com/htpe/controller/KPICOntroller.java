package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.Kpi;
import com.htpe.bean.Report;
import com.htpe.service.KPIService;
import com.htpe.utils.ResultMsg;

@RestController
public class KPICOntroller {
	
	@Autowired
	KPIService kpiService;
	
	/**
	 *過期盤包比例
	 */
	@GetMapping("/kpi/01")
	public ResultMsg  kip01(Kpi kpi) {		
	    return   kpiService.kip01(kpi);
	}

}
