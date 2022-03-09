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
	@GetMapping("/udi")
	public ResultMsg  listUDI(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
		

	    return   udiService.listUDI(pageNum,pageSize,searchName);
	}
	
	/**
	 * 新增單一器械
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/udi")		
	public ResultMsg saveSetno(@Valid @ModelAttribute  CsrUdi csrUdi,HttpServletRequest request,
			@RequestParam(value = "pic1", required = false) MultipartFile pic1,
			@RequestParam(value = "pic2", required = false) MultipartFile pic2,
			@RequestParam(value = "pic3", required = false) MultipartFile pic3,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3) throws RequestPeriodException{	
		return udiService.saveUDI(csrUdi,request,pic1,pic2,pic3,file1,file2,file3);		
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
	public ResultMsg updateUDIById(@Valid @ModelAttribute  CsrUdi csrUdi,
			@PathVariable Integer id,HttpServletRequest request,
			@RequestParam(value = "pic1", required = false) MultipartFile pic1,
			@RequestParam(value = "pic2", required = false) MultipartFile pic2,
			@RequestParam(value = "pic3", required = false) MultipartFile pic3,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3) throws RequestPeriodException{		
		return udiService.updateUDIById(csrUdi,id,request,pic1,pic2,pic3,file1,file2,file3);
	}
	
	/**
	 * 刪除圖片
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/udi/{id}/{filename}")
	public ResultMsg DeleteFile(@PathVariable Integer id,@PathVariable String filename) throws RequestPeriodException{			
		return udiService.removeFile(id,filename);
	}
	
	/**
	 * 刪除器械
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/udi/{id}")
	public ResultMsg DeleteFile(@PathVariable Integer id) throws RequestPeriodException{			
		return udiService.removeUDI(id);
	}
	
	
}
