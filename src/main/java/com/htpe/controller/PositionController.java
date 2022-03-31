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

import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.CsrSetdataPosition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.PositionService;
import com.htpe.utils.ResultMsg;

@RestController
public class PositionController {
	
	@Autowired
	PositionService positionService;
	
	/**
	 * 儲位查詢
	 */
	@GetMapping("/position")
	public ResultMsg  listPosition(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return   positionService.listPosition(pageNum,pageSize,searchName);
	}
	
	
	/**
	 * 儲位新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/position")		
	public ResultMsg savePosition(@Valid @RequestBody  CsrSetdataPosition csrSetdataPosition,HttpServletRequest request) throws RequestPeriodException{			
		return positionService.savePosition(csrSetdataPosition,request);
	}
	
	
	/**
	 * 儲位編輯
	 */
	@GetMapping("/position/{id}")
	public ResultMsg getPosition(@PathVariable Integer id){			
		return positionService.getPositionById(id);
	}
	
	
	/**
	 * 儲位修改
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/position/{id}")		
	public ResultMsg updatePosition(@Valid @RequestBody   CsrSetdataPosition csrSetdataPosition, 
			@PathVariable Integer id, HttpServletRequest request) throws RequestPeriodException{			
	return positionService.updatePosition(csrSetdataPosition,id,request);
	}
	
	/**
	 * 儲位刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/position/{id}")
	public ResultMsg removePosition(@PathVariable Integer id) throws RequestPeriodException {
		return positionService.removePosition(id);
	}

}
