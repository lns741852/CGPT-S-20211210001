package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.archivers.zip.X000A_NTFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrPoltld;
import com.htpe.service.WarehousingService;
import com.htpe.utils.ResultMsg;

@RestController
public class WarehousingCountroller {
	
	
	@Autowired
	WarehousingService warehousingService;
	
	/**
	 *滅菌鍋監控查詢
	 */
	@PostMapping("/warehousing/pot")
	public ResultMsg  getWarehousingList(@RequestBody CsrPoltld csrPoltld) {
	    return  warehousingService.getWarehousingList(csrPoltld);
	}
	
	
	/**
	 *入鍋作業
	 */
	@PutMapping("/warehousing")
	public ResultMsg  upadteWarehousing(Integer[] ids,String depno,String usercname,HttpServletRequest request) {
	    return warehousingService.upadteWarehousing(ids,depno,usercname,request);
	}
	
	

}
