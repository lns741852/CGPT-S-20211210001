package com.htpe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.htpe.service.SearchService;
import com.htpe.utils.ResultMsg;

@RestController
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	
	
	/**
	 *物品清單查詢
	 */
	@GetMapping("/search/barcode")
	public ResultMsg  listReprint(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="depno", required = false) String depno,
			@RequestParam(value="barcode", required = false) String barcode,
			@RequestParam(value="setno", required = false) String setno,
			@RequestParam(value="patientno", required = false) String patientno,
			@RequestParam(value="reqno", required = false) String reqno,
			@RequestParam(value="setsn", required = false) String setsn,
			@RequestParam(value="status", required = false) String status,
			@RequestParam(value="timeoutStatus", required = false) String timeoutStatus) {
		
			Map<String, Object> paramMap = new HashMap<String, Object>();
		    paramMap.put("pageNum", pageNum);
		    paramMap.put("pageSize", pageSize);
		    paramMap.put("depno", depno);
		    paramMap.put("barcode", barcode);
		    paramMap.put("setno", setno);
		    paramMap.put("patientno", patientno);
		    paramMap.put("reqno", reqno);
		    paramMap.put("setsn", setsn);
		    paramMap.put("status", status);
		    paramMap.put("timeoutStatus", timeoutStatus);
		
	    return   searchService.getBarcodeByAll(pageNum,pageSize,paramMap);
	}
	

}
