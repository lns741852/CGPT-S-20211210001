package com.htpe.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.Report;
import com.htpe.bean.Report10;
import com.htpe.bean.Report5;
import com.htpe.bean.Report6;
import com.htpe.bean.Report7;
import com.htpe.bean.Report8;
import com.htpe.bean.Report9;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.mapper.nnew.ReportMapper;
import com.htpe.service.ReportService;
import com.htpe.utils.DateUtils;
import com.htpe.utils.ResultMsg;

@Service
public class ReportServiceImpl  implements ReportService{
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	ReportMapper reportMapper;
	
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
			List<Map<String, Object>> timeoutNums = csrBarcodeMapper.getTimeoutNum(e.getSetno(),start2,end2,todayEnd);						
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

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
			List<Map<String, Object>> timeoutNums= csrBarcodeMapper.getTimeoutNum(e.getSetno(),start2,end2,todayEnd);						
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

		});
		
		return  list;
	}

	@Override
	public ResultMsg listReport03(Report report) {	
		List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.listReport03(report);
		
		List<CsrSetdata3m> list = new ArrayList<>();
		
		timeoutSetno.forEach((e) -> {	
			List<Map<String, Object>> timeoutNums= csrBarcodeMapper.getReport03Count(e.getSetno(),report);		
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

		});
		
		return  ResultMsg.success("每月製作盤包種類及數目分佈圖").addData(list);
	}
	
	
	@Override
	public List<CsrSetdata3m> exportReport03(Report report) {
		List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.listReport03(report);
		
		List<CsrSetdata3m> list = new ArrayList<>();

						
		timeoutSetno.forEach((e) -> {	
			List<Map<String, Object>> timeoutNums= csrBarcodeMapper.getReport03Count(e.getSetno(),report);		
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

		});
		
		return  list;
	}
	
	
	@Override
	public ResultMsg listReport04(Report report) {
		List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.listReport03(report);
		
		List<CsrSetdata3m> list = new ArrayList<>();
						
		timeoutSetno.forEach((e) -> {		
			List<Map<String, Object>> timeoutNums = csrBarcodeMapper.getReport04Count(e.getSetno(),report);							
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

		});
		
		return  ResultMsg.success("人員操作盤包數量統計").addData(list);
	}

	@Override
	public List<CsrSetdata3m> exportReport04(Report report) {
	List<CsrSetdata3m> timeoutSetno = csrBarcodeMapper.listReport03(report);
		
		List<CsrSetdata3m> list = new ArrayList<>();
					
		timeoutSetno.forEach((e) -> {	
			List<Map<String, Object>> timeoutNums= csrBarcodeMapper.getReport04Count(e.getSetno(),report);		
			timeoutNums.forEach(i ->{
				depnoCount(i,e);
			});
			list.add(e);

		});	
		return  list;
	}


	@Override
	public ResultMsg listReport05(Report report) {
		return ResultMsg.success("申領作業統計表").addData(csrRequesitionMapper.listReport05(report));
	}
	
	@Override
	public List<Report5> exportReport05(Report report) {
		return csrRequesitionMapper.listReport05(report);
	}
	
	@Override
	public ResultMsg listReport06(Report report) {
		return ResultMsg.success("製作及回收統計").addData(reportMapper.listReport06(report));
	}
	
	@Override
	public List<Report6> exportReport06(Report report) {
		 return reportMapper.listReport06(report);
	}
	

	@Override
	public ResultMsg listReport07(Report report) {
		return ResultMsg.success("未使用歸還次數統計報表").addData(reportMapper.listReport07(report));
	}

	@Override
	public List<Report7> exportReport07(Report report) {
		 return reportMapper.listReport07(report);
	}
	
	@Override
	public ResultMsg listReport08(Report report) {
		List<Report8> list = reportMapper.listReport08(report);	
		return ResultMsg.success("滅菌鍋操作及測試紀錄報表").addData(reportMapper.listReport08(report));
	}
	
	
	
	@Override
	public List<Report8> exportReport08(Report report) {
		List<Report8> list = reportMapper.listReport08(report);	
		return reportMapper.listReport08(report);
	}
	
	
	@Override
	public ResultMsg listReport09(Report report) {
		return ResultMsg.success("瑕疵包補輸報表").addData(reportMapper.listReport09(report));
	}
	
	@Override
	public List<Report9> exportReport09(Report report) {
		return reportMapper.listReport09(report);
	}
	
	@Override
	public ResultMsg listReport10(Report report) {
		return ResultMsg.success("緊急申領包盤統計").addData(reportMapper.listReport10(report));
	}

	@Override
	public List<Report10> exportReport10(Report report) {
		return reportMapper.listReport10(report);
	}






	
	//部門數量加總
	public CsrSetdata3m depnoCount(Map<String, Object> timeoutNum, CsrSetdata3m e){	
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
			
		}
		return e;
	}





}
