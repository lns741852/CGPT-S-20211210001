package com.htpe.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrRequesition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.ReceiveService;
import com.htpe.utils.ResultMsg;

@Service
public class ReceiveServiceImpl  implements ReceiveService{
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Override
	public ResultMsg updateBarcode(List<CsrBarcode> csrBarcodes) {
		
		Date date = new Date();
		
		csrBarcodes.forEach(barcode ->{
			barcode.setStatus("6");
			barcode.setReceivetime(date);
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
	    	history.setDepno(barcode.getDepno());
	    	history.setIsused("Y");
	    	history.setAction("J");
	    	history.setUserno(barcode.getUnpotUserno());
	    	history.setUsername(barcode.getUnpotUsername());
   	

			int num2 = csrHistoryMapper.insertHistory(history);
			if(num2 < 1) {
				throw new RequestPeriodException(500, "入庫作業失敗");
			}
			
			
		});
		return ResultMsg.success("物品成功歸還").addData("");
	}

	@Override
	public ResultMsg getBarcode(CsrRequesition csrRequesition) {
		CsrRequesition getreqByNoAndPatient = csrRequesitionMapper.getreqByNoAndPatient(csrRequesition);
		
		if(getreqByNoAndPatient == null) {
			return ResultMsg.success("滅菌鍋查詢").addData("no Data");
		}
				
		return ResultMsg.success("滅菌鍋查詢").addData(csrBarcodeMapper.getBarcodeByReqIdForReceive(getreqByNoAndPatient.getReqId()));
	}

}
