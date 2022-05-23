package com.htpe.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;import org.apache.xmlbeans.impl.xb.xmlconfig.NamespaceList.Member2.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrPoltld;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrPoltldMapper;
import com.htpe.service.WarehousingService;
import com.htpe.utils.ResultMsg;

@Service
public class WarehousingServiceImpl  implements WarehousingService{
	
	@Autowired
	CsrBarcodeMapper carBarcodeMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;

	@Override
	public ResultMsg getWarehousingList(CsrPoltld csrPoltld) {
		return ResultMsg.success("h").addData(carBarcodeMapper.getWarehousingList(csrPoltld));
	}

	@Override
	public ResultMsg upadteWarehousing(Integer[] ids,String depno,String usercname,HttpServletRequest request) {		
		String userno = request.getUserPrincipal().getName();
		Date date = new Date();
		
		int i = carBarcodeMapper.upadteWarehousing(ids,depno,userno,usercname,date);		
		
		if(ids.length != i) {
			return ResultMsg.fail(500, "入庫失敗").addData("");
			
		}
		
		List<String> barcodes = carBarcodeMapper.getBarcodeById(ids);
		
		for(String barcode: barcodes) {
			
			CsrHistory history = new CsrHistory();		    
	    	history.setIslast("N");
	    	history.setBarcode(barcode);
	    	
			int updateCount1 = csrHistoryMapper.updateHistory(history);
			if(updateCount1 < 1) {
				throw new RequestPeriodException(500, "入庫作業失敗");
			}
			
			int countHistory = csrHistoryMapper.countHistory(history);
			
	    	history.setReqId(0);
	    	history.setBarcode(barcode);
	    	history.setDutyno(userno);
	    	history.setDutyname(usercname);
	    	history.setUsertime(date);
	    	history.setSn(countHistory+1);
	    	history.setIslast("Y");
	    	history.setDepno(depno);
	    	history.setIsused("N");
	    	history.setAction("Y");
	    	history.setLocation(depno);

	  	
			int num2 = csrHistoryMapper.insertHistory(history);
			if(num2 < 1) {
				throw new RequestPeriodException(500, "入庫作業失敗");
			}
			
		}
		

		
		
		return ResultMsg.success("入庫成功").addData("");
		
	}

}
