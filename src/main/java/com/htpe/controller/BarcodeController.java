package com.htpe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrBarcode;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.BarcodeService;
import com.htpe.utils.ResultMsg;



@RestController
public class BarcodeController {
	
	@Autowired
	BarcodeService barcodeService;
	
	/**
	 * 標籤製作
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/tag/make")		
	public ResultMsg savePrinter(@RequestBody List<CsrBarcode> csrBarcodes,HttpServletRequest request){		
		return barcodeService.saveBarcode(csrBarcodes,request);
	}
	
	/**
	 * 標籤重印
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/tag/reprint")		
	public ResultMsg reprint(@RequestBody List<CsrBarcode> csrBarcodes,HttpServletRequest request){		
		return barcodeService.reprint(csrBarcodes,request);
	}
	
	
	/**
	 *標籤重印查詢
	 */
	@GetMapping("/reprint")
	public ResultMsg  listReprint(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="depno", required = false) String depno,
			@RequestParam(value="barcode", required = false) String barcode,
			@RequestParam(value="setno", required = false) String setno) {
	    return   barcodeService.listReprint(pageNum,pageSize,depno,barcode,setno);
	}
	
	
	
	
	
	
}
