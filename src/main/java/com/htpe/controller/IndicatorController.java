package com.htpe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.utils.ResultMsg;

@RestController
public class IndicatorController {
	
//	/**
//	 *滅菌鍋監控查詢
//	 */
//	@GetMapping("/indicator")
//	public ResultMsg  listReprint(
//			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
//			@RequestParam(value="depno", required = false) String depno,
//			@RequestParam(value="barcode", required = false) String barcode,
//			@RequestParam(value="setno", required = false) String setno) {
//	    return   barcodeService.listReprint(pageNum,pageSize,depno,barcode,setno);
//	}

}
