package com.htpe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.service.ReceiveService;
import com.htpe.utils.ResultMsg;

@RestController
public class ReceiveController {

	@Autowired
	ReceiveService receiveService;
	
	
	@PutMapping("/receive")
	public ResultMsg updateBarcode(@RequestBody List<CsrBarcode> csrBarcodes) {
		return receiveService.updateBarcode(csrBarcodes);
	}
	
	@PostMapping("/receive/req")
	public ResultMsg  getBarcode(@RequestBody CsrRequesition csrRequesition) {
	    return   receiveService.getBarcode(csrRequesition);
	}
	
	
	
}
