package com.htpe.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.mapper.nnew.CsrBarcodeMapper;

import com.htpe.service.ReportService;
import com.htpe.utils.DateUtils;
import com.htpe.utils.ResultMsg;

@Service
public class ReportServiceImpl  implements ReportService{
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	/**
	 * 每月過期包一覽表(CSR)
	 * @param start 開始時間
	 * @param end 結束時間
	 */
	@Override
	public ResultMsg listReport02(Integer pageNum, Integer pageSize,String start ,String end) {
	
		String dateFormat = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
		String todayEnd = DateUtils.startAndEnd(dateFormat, "end");
		String start2  = DateUtils.startAndEnd(start, "start");	
		String end2  = DateUtils.startAndEnd(end, "end");
			
		List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.timeoutSetno(start2,end2,todayEnd);
		
		List<CsrSetdata3m> list = new ArrayList<>();
				
		
		timeoutSetno.forEach((e) -> {			

			Map<String, Object> timeoutNum = csrBarcodeMapper.getTimeoutNum(e.getSetno(),start2,end2,todayEnd);				
			if(timeoutNum != null) {		
				if(e.getTotal() == null) {
					e.setTotal(0);
					e.setNumbaseCsr(0);
					e.setNumbaseCsr2(0);
					e.setNumbaseOr1(0);
					e.setNumbaseOr2(0);
					e.setNumbaseOr3(0);
					e.setNumbaseWr1(0);
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("CSR")) {
					e.setNumbaseCsr((Integer)timeoutNum.get("MATHBARCODE"));	
						e.setTotal(e.getTotal()+e.getNumbaseCsr());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("CSR2")) {
					e.setNumbaseCsr2((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseCsr2());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR1")) {
					e.setNumbaseOr1((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr1());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR2")) {
					e.setNumbaseOr2((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr2());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR3")) {
					e.setNumbaseOr3((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr3());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("WR1")) {
					e.setNumbaseWr1((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseWr1());
				}
				
				list.add(e);
			}
					
		});
		
		return  ResultMsg.success("每月過期包一覽表(CSR)").addData(list);
	}

	@Override
	public List<CsrSetdata3m> exportReport02(Integer pageNum, Integer pageSize, String start, String end) {
		String dateFormat = DateUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
		String todayEnd = DateUtils.startAndEnd(dateFormat, "end");
		String start2  = DateUtils.startAndEnd(start, "start");	
		String end2  = DateUtils.startAndEnd(end, "end");
			
		List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.timeoutSetno(start2,end2,todayEnd);
		
		List<CsrSetdata3m> list = new ArrayList<>();
				
		
		timeoutSetno.forEach((e) -> {			

			Map<String, Object> timeoutNum = csrBarcodeMapper.getTimeoutNum(e.getSetno(),start2,end2,todayEnd);				
			if(timeoutNum != null) {		
				if(e.getTotal() == null) {
					e.setTotal(0);
					e.setNumbaseCsr(0);
					e.setNumbaseCsr2(0);
					e.setNumbaseOr1(0);
					e.setNumbaseOr2(0);
					e.setNumbaseOr3(0);
					e.setNumbaseWr1(0);
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("CSR")) {
					e.setNumbaseCsr((Integer)timeoutNum.get("MATHBARCODE"));	
						e.setTotal(e.getTotal()+e.getNumbaseCsr());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("CSR2")) {
					e.setNumbaseCsr2((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseCsr2());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR1")) {
					e.setNumbaseOr1((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr1());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR2")) {
					e.setNumbaseOr2((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr2());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("OR3")) {
					e.setNumbaseOr3((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseOr3());
				}
				if(timeoutNum.get("DEPNO").toString().trim().equals("WR1")) {
					e.setNumbaseWr1((Integer)timeoutNum.get("MATHBARCODE"));
					e.setTotal(e.getTotal()+e.getNumbaseWr1());
				}
				
				list.add(e);
			}
					
		});
		
		return  list;
	}

}
