package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrProcess;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.ProcessService;
import com.htpe.utils.ResultMsg;


@RestController
public class ProcessController {
	
	@Autowired
	ProcessService processService;
	
	/**
	 *包裝查詢
	 */
	@GetMapping("/package")
	public ResultMsg  listPackage(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return   processService.listPackage(pageNum,pageSize,searchName);
	}
	
	/**
	 * 包裝新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/package")		
	public ResultMsg saveProcess(@Valid @RequestBody  CsrProcess csrProcess,
			HttpServletRequest request) throws RequestPeriodException{			
		return processService.saveProcess(csrProcess, request);
	}
	
	/**
	 * 包裝查詢
	 */
	@GetMapping("/package/{id}")
	public ResultMsg getProcessById(@PathVariable Integer id){			
		return processService.getProcessById(id);
	}
	
	/**
	 * 盤包修改
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/package/{id}")		
	public ResultMsg updateProcess(@Valid @RequestBody  CsrProcess csrProcess, 
			@PathVariable Integer id, HttpServletRequest request) throws RequestPeriodException{			
	return processService.updateProcess(csrProcess,id,request);
	}
	
	/**
	 * 包裝刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/package/{id}")
	public ResultMsg removeProcess(@PathVariable Integer id) throws RequestPeriodException {
		return processService.removeProcess(id);
	}
	
	
}
