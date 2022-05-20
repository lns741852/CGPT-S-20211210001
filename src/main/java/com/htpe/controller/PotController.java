package com.htpe.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrPoltld;
import com.htpe.bean.CsrPotdata;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.PotService;
import com.htpe.utils.ResultMsg;

@RestController
public class PotController {

	
	@Autowired
	PotService potService;
	
	/**
	 *入鍋作業Barcode查詢
	 */
	@GetMapping("/pot/barcode/{barcode}")
	public ResultMsg  getbarcodeByname(@PathVariable String barcode) {
	    return   potService.getbarcodeByname(barcode);
	}
	
	/**
	 *入鍋過次查詢
	 */
	@GetMapping("/pot/potsn/{potname}")
	public ResultMsg  getPotsn(@PathVariable String potname) {
	    return   potService.getPotsn(potname);
	}
	
	
	
	/**
	 * 新增入鍋作業
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/pot")
	public ResultMsg savePot(@RequestBody List<CsrPoltld> csrPoltld, HttpServletRequest request) throws RequestPeriodException{
		return potService.savePot(csrPoltld,request);
	}
	
}
