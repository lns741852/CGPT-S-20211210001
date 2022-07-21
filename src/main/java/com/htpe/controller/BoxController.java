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

import com.htpe.bean.CsrBox;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.BoxService;
import com.htpe.utils.ResultMsg;

@RestController
public class BoxController {
	
	@Autowired
	BoxService BoxService;
	
	/**
	 *包裝查詢
	 */
	@GetMapping("/box")
	public ResultMsg  listbox(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return   BoxService.listBox(pageNum,pageSize,searchName);
	}
	
	/**
	 * 包裝新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/box")		
	public ResultMsg saveBox(@Valid @RequestBody  CsrBox csrBox,
			HttpServletRequest request) throws RequestPeriodException{			
		return BoxService.saveBox(csrBox, request);
	}
	
	/**
	 * 包裝查詢
	 */
	@GetMapping("/box/{id}")
	public ResultMsg getBoxById(@PathVariable Integer id){			
		return BoxService.getBoxById(id);
	}
	
	/**
	 * 包裝查詢
	 */
	@GetMapping("/box/name/{name}")
	public ResultMsg getBoxByNameAndStatus(@PathVariable String name){			
		return BoxService.getBoxByNameAndStatus(name);
	}
	
	/**
	 * 包裝查詢
	 */
	@GetMapping("/fix/box/{name}")
	public ResultMsg getBoxByName(@PathVariable String name){			
		return BoxService.getBoxByName(name);
	}
	

	
	/**
	 * 盤包修改
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/box/{id}")		
	public ResultMsg updateBox(@Valid @RequestBody  CsrBox csrBox, 
			@PathVariable Integer id, HttpServletRequest request) throws RequestPeriodException{
	return BoxService.updateBox(csrBox,id,request);
	}
	
	/**
	 * 包裝刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/box/{id}")
	public ResultMsg removeBox(@PathVariable Integer id) throws RequestPeriodException {
		return BoxService.removeBox(id);
	}
	
	

}
