package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrCasecar;
import com.htpe.bean.CsrCasecarSetdata;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrCasecarMapper;
import com.htpe.service.CasecarService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class CasecarServiceImpl implements CasecarService{
	
	@Autowired
	CsrCasecarMapper csrCasecarMapper;

	@Override
	public ResultMsg listCasecar(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> casecarList = csrCasecarMapper.listCasecar(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casecarList);    
		return  ResultMsg.success("�Ӯר��C��").addData(pageInfo);
	}

	@Override
	public ResultMsg saveCasecar(CsrCasecar casecar, HttpServletRequest request) throws RequestPeriodException {
		
		Date date = new Date();	
		casecar.setDatadate(date);
		casecar.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		casecar.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		casecar.setIsdelete("N");
		
		int num = csrCasecarMapper.insertCasecar(casecar);
		if(num < 1) {
			throw new RequestPeriodException(500, "�Ӯר��s�W����");
		}
		
		List<CsrCasecarSetdata> setnos = casecar.getCasecarSetdatas();
		if(setnos != null) {
			for (CsrCasecarSetdata setno : setnos) {
				int num2 =  csrCasecarMapper.insertCasecarSetno(casecar.getCasecarno(), setno.getSetnoId(), setno.getNum());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "�Ӯר��W����");
				}
			}
		}
		
		return ResultMsg.success("�Ӯר��s�W���\").addData("");
	}

	@Override
	public ResultMsg getCasecarByid(Integer id) {		
		return ResultMsg.success("�Ӯר�").addData(csrCasecarMapper.getCasecarById(id));
	}

	@Override
	public ResultMsg updateCasecar(CsrCasecar casecar, HttpServletRequest request, Integer id) throws RequestPeriodException {
		Date date = new Date();	
		casecar.setDatadate(date);
		casecar.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		casecar.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		casecar.setIsdelete("N");
		
		int num = csrCasecarMapper.updateCasecar(casecar);
		if(num < 1) {
			throw new RequestPeriodException(500, "�Ӯר��ק異��");
		}

//		int num3 = csrCasecarMapper.deleteCasecarSetnoByNo(casecar.getCasecarno());
//		if(num3 < 1) {
//			throw new RequestPeriodException(500, "�Ӯר��ק異��");
//		}
		
		List<CsrCasecarSetdata> setnos = casecar.getCasecarSetdatas();
		if(setnos != null) {
			for (CsrCasecarSetdata setno : setnos) {
				int num2 =  csrCasecarMapper.insertCasecarSetno(casecar.getCasecarno(), setno.getSetnoId(), setno.getNum());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "�Ӯ׭ק異��");
				}
			}
		}
		
		return ResultMsg.success("�Ӯ׭ק�W���\").addData("");
	}

	@Override
	public ResultMsg removeCasecar(String no) throws RequestPeriodException {
		int count = csrCasecarMapper.deleteCasecarByNo(no);
		if(count < 1) {
			throw new RequestPeriodException(500, "�Ӯר��R������");
		}
		int num2 = csrCasecarMapper.deleteCasecarSetnoByNo(no);
		if(num2 < 1) {
			throw new RequestPeriodException(500, "�Ӯר��R������");
		}
		return ResultMsg.success("�Ӯר��R�����\").addData("");
	}

}
