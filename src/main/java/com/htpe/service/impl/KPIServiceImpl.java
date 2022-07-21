package com.htpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.Kpi;
import com.htpe.mapper.nnew.KPIMapper;
import com.htpe.service.KPIService;
import com.htpe.utils.ResultMsg;

@Service
public class KPIServiceImpl  implements KPIService{
	
	@Autowired
	KPIMapper kpiMapper;

	@Override
	public ResultMsg kip01(Kpi kpi) {
		
		String rate = "0";
		
		Double timeoutCountForBarcode = kpiMapper.timeoutCountForBarcode(kpi);
		
		Double countForBarcode = kpiMapper.countForBarcode(kpi);
		
	     if(timeoutCountForBarcode > 0 && countForBarcode > 0)
	     {	    	
	    	 timeoutCountForBarcode = (timeoutCountForBarcode/countForBarcode)*100;
	     	 rate = Double.toString(timeoutCountForBarcode);
	     	 if(rate.length() > 5)
	     	 	 rate = rate.substring(0,5);
	     }
	
		return ResultMsg.success("過期盤包比例").addData(rate);
	}

}
