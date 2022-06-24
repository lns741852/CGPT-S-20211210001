package com.htpe.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.DateUtils;

import com.github.pagehelper.PageHelper;
import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.Search;
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
			@RequestParam(value="timeoutStatus", required = false) String timeoutStatus,
			@RequestParam(value="roomno", required = false) String roomno,
			@RequestParam(value="depnoask", required = false) String depnoask) {
		
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
		    paramMap.put("depnoask", depnoask);
		    paramMap.put("roomno", roomno);
		
	    return   searchService.getBarcodeByAll(pageNum,pageSize,paramMap);
	}
	
	/**
	 *物品清單查詢
	 */
	@GetMapping("/search3")
	public ResultMsg  search3(String udi,String barcode) {	
	    return   searchService.search3(udi,barcode);
	}
	
	
	
	
	/**
	 * Barcode資料查詢
	 */
	@GetMapping("/search/barcode/{barcode}")
	public ResultMsg getBarcodeBybarcode(@PathVariable String barcode){			
		return searchService.getBarcodeBybarcode(barcode);
	}
	
	/**
	 * History資料查詢
	 */
	@GetMapping("/search/history/{barcode}")
	public ResultMsg getHistoryBybarocde(@PathVariable String barcode){			
		return searchService.getHistoryBybarocde(barcode);
	}
	
	
	/**
	 * 交易查詢
	 */
	@PostMapping("/search/req")
	public ResultMsg listReqForSearch5(@RequestBody Search search){					
		return searchService.listReqForSearch5(search);
	}
	
	
	
	/**
	 * 單位申請單列表
	 */
	@GetMapping("/search/req/{id}")
	public ResultMsg getReqById(@PathVariable Integer id){					
		return searchService.getReqById(id);
	}
	
	
	
	/**
	 * 單位申請單列表
	 */
	@GetMapping("/search/pot/warehousingn")
	public ResultMsg getPotWarehousing(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
            CsrBarcode csrBarcode){				
		return searchService.getPotWarehousing(pageNum,pageSize,csrBarcode);
	}
	

}
