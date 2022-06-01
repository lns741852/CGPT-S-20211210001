package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrRequesition;
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
	
	/**
	 * 個案車查詢
	 */
	@GetMapping("/apply/casecar/{casecarno}")
	public ResultMsg getCasecerByno(@PathVariable String casecarno){			
		return applyService.getCasecerByno(casecarno);
	}
	
	
	/**
	 * 申領作業
	 */
	@PostMapping("/apply")
	public ResultMsg saveApply(@RequestBody CsrRequesition csrRequesition){			
		return applyService.saveApply(csrRequesition);
	}
	
	/**
	 * 申領列印查詢
	 */
	@GetMapping("/apply/print/{reqId}")
	public ResultMsg getReqPrint(@PathVariable Integer reqId){				
		return applyService.getReqPrint(reqId);
	}
	

	

}
