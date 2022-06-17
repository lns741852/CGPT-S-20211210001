package com.htpe.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.htpe.bean.CsrUdi;
import com.htpe.bean.CsrUdiType;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.UDITypeService;
import com.htpe.utils.ResultMsg;

@RestController
public class UDITypeController {
	
	@Autowired
	UDITypeService udiTypeService;
	
	/**
	 * UDI查詢
	 */
	@GetMapping("/udi_type")
	public ResultMsg  listUDIType(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
		

	    return udiTypeService.listUDIType(pageNum,pageSize,searchName);
	}
	
	
	/**
	 * 新增器械類
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/udi_type")		
	public ResultMsg saveUDIType(@ModelAttribute  CsrUdiType csrUdi,HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile[] file) throws RequestPeriodException{	
		return udiTypeService.saveUDIType(csrUdi,request,file);		
	}
	
	/**
	 * 配盤
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/udi_type/{id}")		
	public ResultMsg saveSetnoUDI(@PathVariable String id,
			@RequestBody List<Map<String, Object>> csrUdiTypes)	{
		return udiTypeService.saveSetnoUDI(id,csrUdiTypes);		
	}


	
	/**
	 * 編輯器械查詢
	 */
	@GetMapping("/setno/udi/{id}")
	public ResultMsg getSetnoUDI(@PathVariable Integer id){			
		return udiTypeService.getSetnoUDI(id);
	}
	
	/**
	 * 編輯器械查詢
	 */
	@GetMapping("/udi_type/{id}")
	public ResultMsg getUDITypeById(@PathVariable Integer id){			
		return udiTypeService.getUDITypeById(id);
	}
	
	/**
	 * 修改器械類
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/udi_type/{id}")		
	public ResultMsg updateUDITypeById(@ModelAttribute  CsrUdiType csrUdi,HttpServletRequest request, 
			@PathVariable Integer id,
			@RequestParam(value = "file", required = false) MultipartFile[] file) throws RequestPeriodException{	
		
		csrUdi.setId(id);
		return udiTypeService.updateUDITypeById(csrUdi,request,file);		
	}

	
	
	
		
	
}
