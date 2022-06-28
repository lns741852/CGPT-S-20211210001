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
		return  ResultMsg.success("個案車列表").addData(pageInfo);
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
			throw new RequestPeriodException(500, "個案車新增失敗");
		}
		
		List<CsrCasecarSetdata> setnos = casecar.getCasecarSetdatas();
		if(setnos != null) {
			for (CsrCasecarSetdata setno : setnos) {
				int num2 =  csrCasecarMapper.insertCasecarSetno(casecar.getCasecarno(), setno.getCsrSetdata3m().getSetno(), setno.getNum());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "個案車新增失敗");
				}
			}
		}
		
		return ResultMsg.success("個案車新增成功").addData("");
	}

	@Override
	public ResultMsg getCasecarByid(Integer id) {		
		return ResultMsg.success("個案車").addData(csrCasecarMapper.getCasecarById(id));
	}

	@Override
	public ResultMsg updateCasecar(CsrCasecar casecar, HttpServletRequest request) throws RequestPeriodException {
		Date date = new Date();	
		casecar.setDatadate(date);
		casecar.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		casecar.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		casecar.setIsdelete("N");
		
		int num = csrCasecarMapper.updateCasecar(casecar);
		if(num < 1) {
			throw new RequestPeriodException(500, "個案車修改失敗");
		}

		csrCasecarMapper.deleteCasecarSetnoByNo(casecar.getCasecarno());
		
		List<CsrCasecarSetdata> setnos = casecar.getCasecarSetdatas();
		if(setnos != null) {
			for (CsrCasecarSetdata setno : setnos) {
				int num2 =  csrCasecarMapper.insertCasecarSetno(casecar.getCasecarno(), setno.getCsrSetdata3m().getSetno(), setno.getNum());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "個案車修改失敗");
				}
			}
		}
		
		return ResultMsg.success("個案車修改成功").addData("");
	}

	@Override
	public ResultMsg removeCasecar(String no) throws RequestPeriodException {
		int count = csrCasecarMapper.deleteCasecarByNo(no);
		if(count < 1) {
			throw new RequestPeriodException(500, "個案車刪除失敗");
		}
				
		csrCasecarMapper.deleteCasecarSetnoByNo(no);
		
		csrCasecarMapper.deleteCasecarSurgical(no);

		return ResultMsg.success("個案車刪除成功").addData("");
	}

	@Override
	public ResultMsg getCasecarAll() {
		return ResultMsg.success("所有個案車查詢").addData(csrCasecarMapper.getCasecarAll());
	}

	@Override
	public ResultMsg getSetnoByNo(String casecarno) {
		return ResultMsg.success("盤包查詢").addData(csrCasecarMapper.getCasecarByNo(casecarno));
	}

	@Override
	public ResultMsg getCasecerByNo(String no) {
		CsrCasecar casecarByNo = csrCasecarMapper.getCasecarByNo(no);	
		return ResultMsg.success("個案車").addData(csrCasecarMapper.getCasecarById(casecarByNo.getCasecarId()));
	}

}
