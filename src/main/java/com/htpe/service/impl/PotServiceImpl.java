package com.htpe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.archivers.zip.X000A_NTFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrPoltld;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrPoltldMapper;
import com.htpe.service.PotService;
import com.htpe.utils.ResultMsg;

@Service
public class PotServiceImpl implements PotService{
	
	@Autowired
	CsrPoltldMapper csrPoltldMapper;
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;
	
	@Override
	public ResultMsg getbarcodeByname(String barcode) {
		CsrBarcode barocdeRes = csrBarcodeMapper.getbarcodeByname(barcode);
			
		if(barocdeRes == null || !(barocdeRes.getStatus().equals("1"))) {
			throw new RequestPeriodException(500, "條碼無法使用");
		}
		
		Date date = new Date();		
		if(date.compareTo(barocdeRes.getEffectivedate())> 0) {
			throw new RequestPeriodException(500, "條碼已過期");
		};

	
		return ResultMsg.success("條碼可使用").addData("nothing to do");
	}

	@Override
	public ResultMsg savePot(List<CsrPoltld> csrPoltld, HttpServletRequest request) {
		
		Date date = new Date();		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		int i =1;

		for(CsrPoltld pot: csrPoltld) {
			
			if(i==1) {
				CsrPoltld selectPot = csrPoltldMapper.selectPot(pot.getDepno(),pot.getPotname(),pot.getPotno(),pot.getPotsn(),df.format(date));
				if(selectPot == null) {
					pot.setPotscantime(date);			
					int potCount = csrPoltldMapper.insertPot(pot);
					if(potCount<1) {
						throw new RequestPeriodException(500, "入鍋失敗");
					}			
				}else {
					pot.setId(selectPot.getId()); 
				}
			}		
						
			int updateCount= csrBarcodeMapper.updateBarcodeByName(pot.getBarcode(),pot.getId(),pot.getDepno());
			if(updateCount<1) {
				throw new RequestPeriodException(500, "入鍋失敗");
			}
			
        	CsrHistory history = new CsrHistory();		    
        	history.setIslast("N");
        	history.setBarcode(pot.getBarcode());
        	
    		int updateCount1 = csrHistoryMapper.updateHistory(history);
    		if(updateCount1 < 1) {
    			throw new RequestPeriodException(500, "入庫作業失敗");
    		}
    		
        	history.setReqId(0);
        	history.setBarcode(pot.getBarcode());
        	history.setDutyno(pot.getDatauserno());
        	history.setDutyname(pot.getDatausername());
        	history.setUsertime(date);
        	history.setSn(2);
        	history.setIslast("Y");
        	history.setDepno(pot.getDepno());
        	history.setIsused("N");
        	history.setAction("X");
        	history.setLocation(pot.getDepno());
        	history.setPotname(pot.getPotno());
        	history.setPotno(pot.getPotname());
        	history.setPotsn(pot.getPotsn());
        	history.setPotid(pot.getId());
        	
    		int num2 = csrHistoryMapper.insertHistory(history);
    		if(num2 < 1) {
    			throw new RequestPeriodException(500, "入庫作業失敗");
    		}
				
		}
		return ResultMsg.success("入鍋成功").addData("");
	}

	@Override
	public ResultMsg getPotsn(String potname) {		
		int count = csrPoltldMapper.getPotsnCount(potname);		
		if(count == 1) {
			System.out.println(csrPoltldMapper.getPotsnByName(potname));
			return ResultMsg.success("當日過次查詢").addData(csrPoltldMapper.getPotsnByName(potname));
		}
		return ResultMsg.success("當日過次查詢").addData(-1);
	}


}
