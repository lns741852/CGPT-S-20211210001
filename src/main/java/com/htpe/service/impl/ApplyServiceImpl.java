package com.htpe.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.compress.archivers.zip.X000A_NTFS;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrCostcenter;
import com.htpe.bean.CsrDepno;
import com.htpe.bean.CsrReqdetail;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrCasecarMapper;
import com.htpe.mapper.nnew.CsrCostcenterMapper;
import com.htpe.mapper.nnew.CsrDepnoMapper;
import com.htpe.mapper.nnew.CsrReqdetailMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.mapper.nnew.CsrSetdata3mMapper;
import com.htpe.service.ApplyService;
import com.htpe.utils.ResultMsg;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrCostcenterMapper costcenterMapper;
	
	@Autowired
	CsrCasecarMapper csrCasecarMapper;
	
	@Autowired
	CsrReqdetailMapper csrReqdetailMapper;
	
	@Autowired
	CsrDepnoMapper csrDepnoMapper;
	
	@Autowired
	CsrSetdata3mMapper csrSetdata3mMapper;
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;

	
	@Override
	public ResultMsg listCostcenter() {
		return ResultMsg.success("病房").addData(costcenterMapper.listCostcenter());
	}

	@Override
	public ResultMsg getCasecerByno(String casecarno) {
		return ResultMsg.success("個案車").addData(csrCasecarMapper.getCasecarByno(casecarno));
	}

	@Override
	public ResultMsg saveApply(CsrRequesition csrRequesition) {
		Date date = new Date();		
		csrRequesition.setReqno(DateFormatUtils.format(date, "yyyyMMddHHmmSSss"));
		csrRequesition.setDatatime(date);
		csrRequesition.setChecking("N");
		csrRequesition.setUseUp("N");
		csrRequesition.setAllocatetime(null);
		csrRequesition.setDeltime(null);
		
		if(csrRequesition.getRoomno().equals("")) {
			csrRequesition.setRoomno("All");
		}		
		
		csrRequesition.setAllocatetype("G");
		
		csrRequesitionMapper.insertApply(csrRequesition);
		
		List<CsrReqdetail> reqdetails = csrRequesition.getReqdetails();
		
		for(CsrReqdetail req : reqdetails) {
			req.setReqId(csrRequesition.getReqId());
			req.setAllocate("N");
			req.setRealcount(0);
			req.setExchangecount(0);
			
			csrReqdetailMapper.insertReqdetail(req);
			
		}

		return ResultMsg.success("申領成功").addData(csrRequesition.getReqId());
	}

	@Override
	public ResultMsg getReqPrint(Integer reqId) {
		
		CsrRequesition reqPrint = csrRequesitionMapper.getReqPrint(reqId);
			
		CsrDepno depnoByName = csrDepnoMapper.getDepnoByName(reqPrint.getDepnoask());
		reqPrint.setDepnoask(depnoByName.getDepname());
				
		CsrDepno depnoByName2 = csrDepnoMapper.getDepnoByName(reqPrint.getDepno());
		reqPrint.setDepno(depnoByName2.getDepname());
		
		if(!reqPrint.getCenterno().equals("")) {
			CsrCostcenter centerByno = csrRequesitionMapper.getCenterByno(reqPrint.getCenterno());
			reqPrint.setCenterno(centerByno.getCentername());
		}

		
		for(CsrReqdetail csrReqdetail : reqPrint.getReqdetails()) {
			CsrSetdata3m setnoByNo = csrSetdata3mMapper.getSetnoByNo(csrReqdetail.getSetno());
			csrReqdetail.setSetname(setnoByNo.getSetnamech());
			
			List<CsrBarcode> barcodeByRdid = csrBarcodeMapper.getBarcodeByRdid(csrReqdetail.getRdId());			
			csrReqdetail.setBarcodeName(barcodeByRdid);
		}

		
		return ResultMsg.success("申領單").addData(reqPrint);
	}

	@Override
	public ResultMsg saveRelocate(CsrRequesition csrRequesition) {
		Date date = new Date();		
		csrRequesition.setReqno(DateFormatUtils.format(date, "yyyyMMddHHmmSSss"));
		csrRequesition.setDatatime(date);
		csrRequesition.setChecking("Y");
		csrRequesition.setUseUp("N");
		csrRequesition.setAllocatetime(date);
		csrRequesition.setDeltime(null);
		
		if(csrRequesition.getRoomno().equals("")) {
			csrRequesition.setRoomno("All");
		}		
		
		
		csrRequesitionMapper.insertApply(csrRequesition);
		
		List<CsrReqdetail> reqdetails = csrRequesition.getReqdetails();
				
		for(CsrReqdetail req : reqdetails) {			
			req.setReqId(csrRequesition.getReqId());
			req.setAllocate(csrRequesition.getAllocatetype());
			req.setSetcount(1);
			req.setRealcount(1);
			req.setExchangecount(0);
			
			csrReqdetailMapper.insertReqdetail(req);
			
		}
			
		return ResultMsg.success("補輸成功").addData(csrRequesition.getReqId());
	}


}
