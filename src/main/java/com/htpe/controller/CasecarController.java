package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrCasecar;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.CasecarService;
import com.htpe.utils.ResultMsg;

@RestController
public class CasecarController {
	
	@Autowired
	CasecarService casecarService;
	
	/**
	 * 個案車列表查詢
	 */
	@GetMapping("/casecar")
	public ResultMsg  listCasecar(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return casecarService.listCasecar(pageNum,pageSize,searchName);	  
	}
	
	/**
	 * 新增個案車
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/casecar")
	public ResultMsg saveCasecar(@RequestBody CsrCasecar csrCasecar,HttpServletRequest request) throws RequestPeriodException{
		return casecarService.saveCasecar(csrCasecar,request);
	}
	
	
	/**
	 * 編輯個案車查詢
	 */
	@GetMapping("/casecar/{id}")
	public ResultMsg getCasecerById(@PathVariable Integer id){			
		return casecarService.getCasecarByid(id);
	}
	
	/**
	 * 修改個案車
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/casecar/{id}")
	public ResultMsg updateCasecar(@RequestBody CsrCasecar csrCasecar,HttpServletRequest request,
			@PathVariable Integer id) throws RequestPeriodException{
		csrCasecar.setCasecarId(id);
		return casecarService.updateCasecar(csrCasecar,request);
	}
	
	
	/**
	 * 刪除個案車
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/casecar/{no}")
	public ResultMsg removeCasecar(@PathVariable String no) throws RequestPeriodException {
		return casecarService.removeCasecar(no);
	}

	

}
