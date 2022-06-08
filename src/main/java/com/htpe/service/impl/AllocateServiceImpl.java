package com.htpe.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrReqdetail;
import com.htpe.bean.CsrRequesition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrReqdetailMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.AllocateService;
import com.htpe.utils.DateUtils;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class AllocateServiceImpl  implements AllocateService{
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrReqdetailMapper csrReqdetailMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;

	@Override
	public ResultMsg listAllocate(String depno) {
		List<CsrRequesition> reqByDepno = csrRequesitionMapper.getReqByDepno(depno);
		CollectionUtils.isEmpty(reqByDepno);		
		return ResultMsg.success("申領確認列表").addData(reqByDepno);
	}

	@Override
	public ResultMsg removeAllocate(Integer id, HttpServletRequest request) {

		CsrRequesition csrRequesition = new CsrRequesition();
		Date date = new Date();	
		
		csrRequesition.setReqId(id);
		csrRequesition.setDeltime(date);
		csrRequesition.setDeluserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrRequesition.setDelusername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrRequesition.setChecking("C");
		
		int num = csrRequesitionMapper.updateReq(csrRequesition);
		if(num < 1) {
			throw new RequestPeriodException(500, "刪除失敗");
		}
		
		return ResultMsg.success("刪除成功").addData("");
	}

	@Override
	public ResultMsg getAllocate(Integer id) {
		return ResultMsg.success("申領確認列表").addData(csrRequesitionMapper.getReqByIdforChecking(id));
	}

	@Override
	public ResultMsg getbarcodeByname(String barcode) {
		CsrBarcode barocdeRes = csrBarcodeMapper.getbarcodeByname(barcode);
		
		if(barocdeRes == null || !(barocdeRes.getStatus().equals("3"))) {
			throw new RequestPeriodException(500, "條碼無法使用");
		}
		
		Date date = new Date();		
		if(date.compareTo(barocdeRes.getEffectivedate())> 0) {
			throw new RequestPeriodException(500, "條碼已過期");
		};
	
		return ResultMsg.success("條碼可使用").addData(barocdeRes);
	}

	@Override
	public ResultMsg updateAllocate(CsrRequesition csrRequesition) {
		Date date = new Date();
		csrRequesition.setAllocatetime(date);
		csrRequesition.setChecking("Y");
		
		csrRequesitionMapper.updateReq(csrRequesition);
		
		List<CsrReqdetail> reqdetails = csrRequesition.getReqdetails();
		
		for(CsrReqdetail req : reqdetails) {
			req.setAllocate("Y");
			csrReqdetailMapper.updateReqDetail(req);
			
		}	
		return ResultMsg.success("申領確認").addData("nothing to do");
	}

	@Override
	public ResultMsg updateBarcode(Integer id, List<CsrBarcode> csrBarcodes) {
		
		Date date = new Date();
		CsrRequesition reqById = csrRequesitionMapper.getReqPrint(id);
		
		List<CsrReqdetail> reqdetails = reqById.getReqdetails();
		
		for(CsrReqdetail req : reqdetails) {

			for(CsrBarcode barcode : csrBarcodes) {
				barcode.setReqId(id);
				barcode.setStatus("4");	
				barcode.setPatientno(reqById.getPatientno());
				if(req.getSetno().equals(barcode.getSetno()) && (barcode.getRdId() == null)) {	
					barcode.setRdId(req.getRdId());
					csrBarcodeMapper.updateBarcodeById(barcode);
				}
						
				CsrHistory history = new CsrHistory();		    
	        	history.setIslast("N");
	        	history.setBarcode(barcode.getBarcode());
	        	      	
	        	
	    		int updateCount1 = csrHistoryMapper.updateHistory(history);
	    		if(updateCount1 < 1) {
	    			throw new RequestPeriodException(500, "配送失敗");
	    		}
	    		
				int countHistory = csrHistoryMapper.countHistory(history);
	    		
	        	history.setReqId(id);
	        	history.setBarcode(barcode.getBarcode());
	        	history.setUsertime(date);
	        	history.setSn(countHistory+1);
	        	history.setIslast("Y");
	        	history.setDepno(reqById.getDepno());
	        	history.setIsused("N");
	        	history.setAction("A");
	        	history.setLocation(reqById.getDepnoask());
	        	history.setUserno(reqById.getEmpno());
	        	history.setUsername(reqById.getUsercname());
	        	history.setDutyno(reqById.getDatauserno());
	        	history.setDutyname(reqById.getDatausername());
	        	history.setPatientno(reqById.getPatientno());
	
	    		int num2 = csrHistoryMapper.insertHistory(history);
	    		if(num2 < 1) {
	    			throw new RequestPeriodException(500, "配送失敗");
	    		}
		
			}
		}
			
		return ResultMsg.success("申領確認成功").addData("");
	}

}
