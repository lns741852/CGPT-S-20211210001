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

import com.htpe.bean.CsrPotdata;
import com.htpe.bean.CsrSurgical;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.DisinfectionService;
import com.htpe.utils.ResultMsg;

@RestController
public class DisinfectionController {
	
	@Autowired
	DisinfectionService disinfectionService;
	
	/**
	 * 消毒鍋列表查詢
	 */
	@GetMapping("/disinfection")
	public ResultMsg  listDisinfection(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return disinfectionService.listDisinfection(pageNum,pageSize,searchName);	  
	}
	
	/**
	 * 
	 * 消毒包裝查詢
	 */
	@GetMapping("/disinfection/process")
	public ResultMsg  listProcess() {
	    return   disinfectionService.listProcess();
	}
	
	/**
	 * 新增消毒鍋
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/disinfection")
	public ResultMsg saveSurgical(@Valid @RequestBody CsrPotdata csrPotdata, HttpServletRequest request) throws RequestPeriodException{
		return disinfectionService.saveDisinfection(csrPotdata,request);
	}
	
	/**
	 * 編輯消毒鍋查詢
	 */
	@GetMapping("/disinfection/{id}")
	public ResultMsg getSurgicalById(@PathVariable Integer id){	
		return disinfectionService.getPotdataById(id);
	}
	
	/**
	 * 修改消毒鍋
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/disinfection/{id}")
	public ResultMsg updateSurgical(@Valid @RequestBody CsrPotdata csrPotdata,HttpServletRequest request,
			@PathVariable Integer id) throws RequestPeriodException{
		csrPotdata.setId(id);
		return disinfectionService.updatePotdata(csrPotdata,request);
	}
	
	
	/**
	 * 刪除消毒鍋
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/disinfection/{no}")
	public ResultMsg removeSurgical(@PathVariable String no) throws RequestPeriodException {
		return disinfectionService.removePotdata(no);
	}
	

}
