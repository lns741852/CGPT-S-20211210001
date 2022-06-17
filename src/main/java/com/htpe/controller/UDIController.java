package com.htpe.controller;


import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.UDIService;
import com.htpe.utils.ResultMsg;

@RestController
public class UDIController {
	
	@Autowired
	UDIService udiService;
	
	/**
	 * UDI查詢
	 */
	@GetMapping("/udi/type/{typeId}")
	public ResultMsg  listUDI(@PathVariable Integer typeId) {
	    return   udiService.listUDI(typeId);
	}
	
	/**
	 * 新增單一器械
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/udi")		
	public ResultMsg saveSetno(@RequestBody  CsrUdi csrUdi,HttpServletRequest request){	
		return udiService.saveUDI(csrUdi,request);		
	}

	
	/**
	 * 編輯器械查詢
	 */
	@GetMapping("/udi/{id}")
	public ResultMsg getUDIById(@PathVariable Integer id){			
		return udiService.getUDIById(id);
	}
	
	/**
	 * 器械修改
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/udi/{id}")
	public ResultMsg updateUDIById(@RequestBody  CsrUdi csrUdi,
			@PathVariable Integer id,HttpServletRequest request) {		
		return udiService.updateUDIById(csrUdi,id,request);
	}
//	
//	/**
//	 * 刪除圖片
//	 * @throws RequestPeriodException 
//	 */
//	@DeleteMapping("/udi/{id}/{filename}")
//	public ResultMsg DeleteFile(@PathVariable Integer id,@PathVariable String filename) throws RequestPeriodException{			
//		return udiService.removeFile(id,filename);
//	}
//	
	/**
	 * 刪除器械
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/udi/{id}")
	public ResultMsg DeleteFile(@PathVariable Integer id) throws RequestPeriodException{			
		return udiService.removeUDI(id);
	}
	
	
	/**
	 * 編輯器械查詢
	 */
	@GetMapping("/udi/name/{udi}")
	public ResultMsg getUDIByName(@PathVariable String udi){			
		return udiService.getUDIByName(udi);
	}
	
	
}
