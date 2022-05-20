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
			SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
			String date = dFormat.format(csrPoltld.getPotscantime());
			csrPoltld.setTime(date);
			
			CsrPoltld pot = csrPoltldMapper.getPot(csrPoltld);
			if(pot != null) {					
				return ResultMsg.success("滅菌監測查詢").addData(pot);
			}
			return ResultMsg.fail(500, "滅菌監測查詢失敗").addData("");
	}

	@Override
	public ResultMsg updateIndicator(CsrPoltld csrPoltld) {		
		csrPoltld.setIsright("Y");		
		csrPoltldMapper.updateIndicator(csrPoltld);
			
		return ResultMsg.success("滅菌監測成功").addData("");		
	}

}
