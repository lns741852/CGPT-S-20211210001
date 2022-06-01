package com.htpe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
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
	
	
	@GetMapping("/allocate2/{id}")
	public ResultMsg  getAllocate(@PathVariable Integer id) {
	    return allocateService.getAllocate(id);	  
	}
	
	
	/**
	 *Barcode查詢
	 */
	@GetMapping("/allocate/barcode/{barcode}")
	public ResultMsg  getbarcodeByname(@PathVariable String barcode) {
	    return   allocateService.getbarcodeByname(barcode);
	}
	
	
	/**
	 *申領單確認
	 */
	@PutMapping("/allocate")
	public ResultMsg  updateAllocate(@RequestBody CsrRequesition csrRequesition) {
	    return   allocateService.updateAllocate(csrRequesition);
	}
	
	/**
	 *申領單Barcode確認
	 */
	@PutMapping("/allocate/{id}")
	public ResultMsg  updateBarcode(@PathVariable Integer id,
			@RequestBody List<CsrBarcode> csrBarcodes) {			
	    return  allocateService.updateBarcode(id,csrBarcodes);
	}
	
	
	
	
	
}
