package com.htpe.service.impl;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrPoltld;
import com.htpe.mapper.nnew.CsrPoltldMapper;
import com.htpe.service.IndicatorService;
import com.htpe.utils.ResultMsg;

@Service
public class IndicatorServiceImpl  implements IndicatorService{
	
	@Autowired
	CsrPoltldMapper csrPoltldMapper;

	@Override
	public ResultMsg getIndicatorByAll(CsrPoltld csrPoltld) {			
		if(csrPoltld != null) {
			
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dFormat.format(csrPoltld.getPotscantime());
			csrPoltld.setTime(date);
					
			return ResultMsg.success("滅菌監測查詢").addData(csrPoltldMapper.getPot(csrPoltld));
		}
	return null;
	}

}
