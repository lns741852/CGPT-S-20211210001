package com.htpe.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrPotDepno;
import com.htpe.bean.CsrPrinter;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.DepnoService;
import com.htpe.utils.ResultMsg;

@RestController
public class DepnoController {
	
	@Autowired
	DepnoService depnoService;
	
	/**
	 * 部門列表查詢
	 */
	@GetMapping("/depno")
	public ResultMsg  listCasecar(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return depnoService.listDepno(pageNum,pageSize,searchName);	  
	}
	
	/**
	 * 部門資料查詢
	 */
	@GetMapping("/depno/{id}")
	public ResultMsg getCasecerById(@PathVariable Integer id){			
		return depnoService.getDepnoById(id);
	}

	/**
	 * 消毒設備新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/depno/potdata")		
	public ResultMsg savePotdata(@Valid @RequestBody  CsrPotDepno csrPotDepno){			
		return depnoService.savePotdata(csrPotDepno);
	}
	
	/**
	 * 消毒設備刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/depno/potdata/{id}")
	public ResultMsg removePotdata(@PathVariable Integer id) throws RequestPeriodException {
		return depnoService.removePotdata(id);
	}
	
	/**
	 * 印表機新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/depno/printer")		
	public ResultMsg savePrinter(@Valid @RequestBody  CsrPrinter csrPrinter){			
		return depnoService.savePrinter(csrPrinter);
	}
	
	/**
	 * 印表機刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/depno/printer/{id}")
	public ResultMsg removePrinter(@PathVariable Integer id) throws RequestPeriodException {
		return depnoService.removePrinter(id);
	}
}
