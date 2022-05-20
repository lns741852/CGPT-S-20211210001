package com.htpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrPoltld;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrPoltldMapper;
import com.htpe.service.WarehousingService;
import com.htpe.utils.ResultMsg;

@Service
public class WarehousingServiceImpl  implements WarehousingService{
	
	@Autowired
	CsrBarcodeMapper carBarcodeMapper;

	@Override
	public ResultMsg getWarehousingList(CsrPoltld csrPoltld) {
		
		
		
		
		return ResultMsg.success("h").addData(carBarcodeMapper.getWarehousingList(csrPoltld));
	}

}
