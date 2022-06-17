package com.htpe.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.map.HashedMap;
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
		CsrRequesition getreqByNoAndPatient = csrRequesitionMapper.getreqByNoAndPatientAndDepno(csrRequesition);
		
		if(getreqByNoAndPatient == null) {
			return ResultMsg.success("滅菌鍋查詢").addData(null);
		}
				
		return ResultMsg.success("滅菌鍋查詢").addData(csrBarcodeMapper.getBarcodeByReqId(getreqByNoAndPatient.getReqId()));
	}

	@Override
	public ResultMsg updateBarcode(List<CsrBarcode> csrBarcodes,HttpServletRequest request) {
		
		Integer reqId = 0;
		Date date = new Date();
		HashSet<Integer> hashSet = new HashSet<Integer>();
		CsrRequesition csrRequesition = new CsrRequesition();
		
		for(CsrBarcode barcode : csrBarcodes) {
			barcode.setStatus("5");
			barcode.setUsing("Y");
			barcode.setUserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
			barcode.setUsername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));		
			csrBarcodeMapper.updateBarcodeById(barcode);
						
			hashSet.add(barcode.getReqId());
			
			if(reqId.equals( barcode.getReqId())) {
				csrRequesition = csrRequesitionMapper.getReqById(reqId);
				reqId = barcode.getReqId();
			}	
			
					
			CsrHistory history = new CsrHistory();		    
	    	history.setIslast("N");
	    	history.setBarcode(barcode.getBarcode());
	    	      	
	    	
			int updateCount1 = csrHistoryMapper.updateHistory(history);
			if(updateCount1 < 1) {
				throw new RequestPeriodException(500, "物品轉移失敗");
			}
			
			int countHistory = csrHistoryMapper.countHistory(history);
			
	    	history.setBarcode(barcode.getBarcode());
	    	history.setUsertime(date);
	    	history.setSn(countHistory+1);
	    	history.setIslast("Y");
	    	history.setDepno(barcode.getLocation());
	    	history.setIsused("Y");
	    	history.setAction("D");
	    	history.setLocation(csrRequesition.getRoomno());
	    	history.setUserno(barcode.getUserno());
	    	history.setUsername(barcode.getUsername());
	    	history.setPatientno(barcode.getPatientno());
	    	

			int num2 = csrHistoryMapper.insertHistory(history);
			if(num2 < 1) {
				throw new RequestPeriodException(500, "物品轉移失敗");
			}
			
							
		}
		
		CsrRequesition csrRequesition2 = new CsrRequesition();
		csrRequesition2.setUseUp("Y");	
		hashSet.forEach(i ->{
			csrRequesition2.setReqId(i);
			csrRequesitionMapper.updateReq(csrRequesition2);			
		});
	

		return ResultMsg.success("病患使用確認").addData("");
	}

	@Override
	public ResultMsg getBarcode2(Map<String, Object>  barcode) {
		return ResultMsg.success("滅菌鍋查詢").addData(csrBarcodeMapper.gebarcodeByNoForUse(barcode));
	}

	@Override
	public ResultMsg getReqByBarcode(Integer id) {
		return ResultMsg.success("申領病房查詢").addData(csrRequesitionMapper.getReqById(id));
	}

	@Override
	public ResultMsg updateReqById(List<CsrBarcode> csrBarcodes, String roomno, HttpServletRequest request) {
		Integer reqId = 0;
		Date date = new Date();
		HashSet<Integer> hashSet = new HashSet<Integer>();
		CsrRequesition csrRequesition = new CsrRequesition();
		
		for(CsrBarcode barcode : csrBarcodes) {
			hashSet.add(barcode.getReqId());
			
			if(reqId.equals( barcode.getReqId())) {
				csrRequesition = csrRequesitionMapper.getReqById(reqId);
				reqId = barcode.getReqId();
			}	
			
									
			CsrHistory history = new CsrHistory();		    
	    	history.setIslast("N");
	    	history.setBarcode(barcode.getBarcode());
	    	      	
	    	
			int updateCount1 = csrHistoryMapper.updateHistory(history);
			if(updateCount1 < 1) {
				throw new RequestPeriodException(500, "物品轉移失敗");
			}
			
			int countHistory = csrHistoryMapper.countHistory(history);
			
	    	history.setBarcode(barcode.getBarcode());
	    	history.setUsertime(date);
	    	history.setSn(countHistory+1);
	    	history.setIslast("Y");
	    	history.setDepno(barcode.getLocation());
	    	history.setAction("C");
	    	history.setLocation(roomno);
	    	history.setUserno(barcode.getUserno());
	    	history.setUsername(barcode.getUsername());
	    	history.setPatientno(barcode.getPatientno());
	    	

			int num2 = csrHistoryMapper.insertHistory(history);
			if(num2 < 1) {
				throw new RequestPeriodException(500, "物品轉移失敗");
			}
			
							
		}
		
		CsrRequesition csrRequesition2 = new CsrRequesition();
		csrRequesition2.setRoomno(roomno);
		csrRequesition2.setAllocatetype("C");
		hashSet.forEach(i ->{
			csrRequesition2.setReqId(i);
			csrRequesitionMapper.updateReq(csrRequesition2);			
		});
	

		return ResultMsg.success("物品轉移成功").addData("");
	}


}
