package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
	
	
}
