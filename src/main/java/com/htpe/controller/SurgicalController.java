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

import com.htpe.bean.CsrCasecar;
import com.htpe.bean.CsrSurgical;
import com.htpe.bean.CsrSurgicalCasecar;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.SurgicalService;
import com.htpe.utils.ResultMsg;

@RestController
public class SurgicalController {
	
	@Autowired
	SurgicalService surgicalService;
	

	/**
	 *手術列表查詢
	 */
	@GetMapping("/surgical")
	public ResultMsg  listSurgical(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return   surgicalService.listSurgical(pageNum,pageSize,searchName);
	}
	
	/**
	 * 
	 * 個案車查詢
	 */
	@GetMapping("/surgical/casecar/{casecarNo}")
	public ResultMsg  getCasecarByNo(@PathVariable String casecarNo) {
		System.out.println(casecarNo);
	    return   surgicalService.getCasecarByNo(casecarNo);
	}
	
	/**
	 * 新增手術
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/surgical")
	public ResultMsg saveSurgical(@Valid @RequestBody CsrSurgical csrSurgical,HttpServletRequest request) throws RequestPeriodException{
		return surgicalService.saveSurgical(csrSurgical,request);
	}
	
	/**
	 * 編輯手術查詢
	 */
	@GetMapping("/surgical/{id}")
	public ResultMsg getSurgicalById(@PathVariable Integer id){	

		return surgicalService.getSurgicalById(id);
	}
	
	/**
	 * 修改手術
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/surgical/{id}")
	public ResultMsg updateSurgical(@Valid @RequestBody CsrSurgical csrSurgical,HttpServletRequest request,
			@PathVariable Integer id) throws RequestPeriodException{
		csrSurgical.setId(id);
		return surgicalService.updateSurgical(csrSurgical,request);
	}
	
	
	/**
	 * 刪除手術
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/surgical/{no}")
	public ResultMsg removeSurgical(@PathVariable String no) throws RequestPeriodException {
		return surgicalService.removeSurgical(no);
	}
	

}
