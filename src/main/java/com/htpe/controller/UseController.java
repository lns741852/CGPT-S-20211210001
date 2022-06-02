package com.htpe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.service.UseService;
import com.htpe.utils.ResultMsg;

@RestController
public class UseController {
	
	@Autowired
	UseService useService;
	
	
	/**
	 *病患使用查詢
	 */
	@PostMapping("/use")
	public ResultMsg  getBarcode(@RequestBody CsrRequesition csrRequesition) {
	    return   useService.getBarcode(csrRequesition);
	}
	
	/**
	 *病患使用查詢
	 */
	@PostMapping("/use/barcode")
	public ResultMsg  getBarcode2(@RequestBody String barcode) {
	    return   useService.getBarcode2(barcode);
	}
	
	/**
	 *病患使用確認
	 */
	@PutMapping("/use")
	public ResultMsg  updateBarcode(@RequestBody List<CsrBarcode> csrBarcodes,HttpServletRequest request) {
	    return   useService.updateBarcode(csrBarcodes,request);
	}
	

}
