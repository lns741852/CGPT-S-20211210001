package com.htpe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrPoltld;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrPoltldMapper;
import com.htpe.service.PotService;
import com.htpe.utils.ResultMsg;

@Service
public class PotServiceImpl implements PotService{
	
	@Autowired
	CsrPoltldMapper csrPoltld;
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Override
	public ResultMsg getbarcodeByname(String barcode) {
		CsrBarcode barocdeRes = csrBarcodeMapper.getbarcodeByname(barcode);
			
		if(barocdeRes == null) {
			throw new RequestPeriodException(500, "條碼錯誤");
		}
		
		Date date = new Date();		
		if(date.compareTo(barocdeRes.getEffectivedate())> 0) {
			throw new RequestPeriodException(500, "條碼已過期");
		};

	
		return ResultMsg.success("條碼可使用").addData("nothing to do");
	}


}
