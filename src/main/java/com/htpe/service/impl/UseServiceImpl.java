package com.htpe.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrReqdetail;
import com.htpe.bean.CsrRequesition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrReqdetailMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.UseService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class UseServiceImpl  implements UseService{
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrReqdetailMapper csrReqdetailMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;

	@Override
	public ResultMsg getBarcode(CsrRequesition csrRequesition) {
		CsrRequesition getreqByNoAndPatient = csrRequesitionMapper.getreqByNoAndPatient(csrRequesition);
		
		if(getreqByNoAndPatient == null) {
			return ResultMsg.success("滅菌鍋查詢").addData("no Data");
		}
				
		return ResultMsg.success("滅菌鍋查詢").addData(csrBarcodeMapper.getBarcodeByReqId(getreqByNoAndPatient.getReqId()));
	}

	@Override
	public ResultMsg updateBarcode(List<CsrBarcode> csrBarcodes,HttpServletRequest request) {
		
		Integer regId = 0;
		Date date = new Date();
		
		for(CsrBarcode barcode : csrBarcodes) {
			barcode.setStatus("5");
			barcode.setUsing("Y");
			barcode.setUserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
			barcode.setUsername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));		
			csrBarcodeMapper.updateBarcodeById(barcode);
					
			CsrHistory history = new CsrHistory();		    
	    	history.setIslast("N");
	    	history.setBarcode(barcode.getBarcode());
	    	      	
	    	
			int updateCount1 = csrHistoryMapper.updateHistory(history);
			if(updateCount1 < 1) {
				throw new RequestPeriodException(500, "入庫作業失敗");
			}
			
			int countHistory = csrHistoryMapper.countHistory(history);
			
	    	history.setReqId(barcode.getReqId());
	    	history.setBarcode(barcode.getBarcode());
	    	history.setUsertime(date);
	    	history.setSn(countHistory+1);
	    	history.setIslast("Y");
	    	history.setDepno(barcode.getLocation());
	    	history.setIsused("Y");
	    	history.setAction("D");
	    	history.setLocation(barcode.getLocation());
	    	history.setUserno(barcode.getUserno());
	    	history.setUsername(barcode.getUsername());
	    	history.setPatientno(barcode.getPatientno());
	    	

			int num2 = csrHistoryMapper.insertHistory(history);
			if(num2 < 1) {
				throw new RequestPeriodException(500, "入庫作業失敗");
			}
			
							
		}
		
		CsrRequesition csrRequesition = new CsrRequesition();
		csrRequesition.setReqId(regId);
		csrRequesition.setUseUp("Y");
		
		csrRequesitionMapper.updateReq(csrRequesition);
			
		
		return ResultMsg.success("病患使用確認").addData("");
	}

	@Override
	public ResultMsg getBarcode2(String barcode) {
		return ResultMsg.success("滅菌鍋查詢").addData(csrBarcodeMapper.gebarcodeByNoForUse(barcode));
	}

}
