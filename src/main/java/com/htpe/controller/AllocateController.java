package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.exception.RequestPeriodException;
import com.htpe.service.AllocateService;
import com.htpe.utils.ResultMsg;

@RestController
public class AllocateController {
	
	
	@Autowired
	AllocateService allocateService;
	
	@GetMapping("/allocate/{depno}")
	public ResultMsg  listAllocate(@PathVariable String depno) {
	    return allocateService.listAllocate(depno);	  
	}

	/**
	 * 刪除申領單
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/allocate/{id}")
	public ResultMsg removeRoom(@PathVariable Integer id,HttpServletRequest request) throws RequestPeriodException {
		return allocateService.removeAllocate(id,request);
	}
}
