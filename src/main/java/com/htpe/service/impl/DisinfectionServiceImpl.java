package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrPotdata;
import com.htpe.bean.CsrProcess;
import com.htpe.bean.CsrProcessPot;
import com.htpe.bean.CsrSurgicalCasecar;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrPotdataMapper;
import com.htpe.mapper.nnew.CsrProcessMapper;
import com.htpe.service.DisinfectionService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class DisinfectionServiceImpl implements DisinfectionService{
	
	@Autowired
	CsrPotdataMapper csrPotdataMapper;
	
	@Autowired
	CsrProcessMapper csrProcessMapper;

	@Override
	public ResultMsg listDisinfection(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> seqList = csrPotdataMapper.listPotdata(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(seqList);    
		return  ResultMsg.success("消毒鍋列表").addData(pageInfo);
	}

	@Override
	public ResultMsg saveDisinfection(CsrPotdata csrPotdata, HttpServletRequest request) {
		Date date = new Date();	
		csrPotdata.setDatadate(date);
		csrPotdata.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrPotdata.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrPotdata.setIsdelete("N");
		
		int num = csrPotdataMapper.insertPotdata(csrPotdata);
		if(num < 1) {
			throw new RequestPeriodException(500, "消毒鍋新增失敗");
		}
		
		List<CsrProcessPot> processes = csrPotdata.getCsrProcessPot();
		if(processes != null) {
			for (CsrProcessPot process : processes) {
				int num2 =  csrPotdataMapper.insertPotdataProcess(csrPotdata.getDisinfection(), process.getProcessno());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "消毒鍋新增失敗");
				}
			}
		}
		
		return ResultMsg.success("消毒鍋新增成功").addData("");
	}

	@Override
	public ResultMsg listProcess() {
		return ResultMsg.success("消毒包裝方式").addData(csrProcessMapper.listProcess(null));
	}

	@Override
	public ResultMsg getPotdataById(Integer id) {
		return ResultMsg.success("消毒鍋").addData(csrPotdataMapper.getPotdataById(id));

	}

	@Override
	public ResultMsg updatePotdata(CsrPotdata csrPotdata, HttpServletRequest request) {
		Date date = new Date();	
		csrPotdata.setDatadate(date);
		csrPotdata.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrPotdata.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrPotdata.setIsdelete("N");
		
		int num = csrPotdataMapper.updatePotdata(csrPotdata);
		if(num < 1) {
			throw new RequestPeriodException(500, "消毒鍋修改失敗");
		}
		
		int num3 = csrPotdataMapper.deletePotdataProcessByNo(csrPotdata.getDisinfection());
		if(num3 < 1) {
			throw new RequestPeriodException(500, "消毒鍋修改失敗");
		}
		
		List<CsrProcessPot> pots = csrPotdata.getCsrProcessPot();
		if(pots != null) {
			for (CsrProcessPot pot : pots) {
				int num2 =  csrPotdataMapper.insertPotdataProcess(csrPotdata.getDisinfection(), pot.getProcessno());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "消毒鍋修改失敗");
				}
			}
		}
		
		return ResultMsg.success("消毒鍋修改成功").addData("");
	}

	@Override
	public ResultMsg removePotdata(String no) {
		int count = csrPotdataMapper.deletePotdata(no);
		if(count < 1) {
			throw new RequestPeriodException(500, "消毒鍋刪除失敗");
		}
		int num2 = csrPotdataMapper.deletePotdataProcessByNo(no);
		if(num2 < 1) {
			throw new RequestPeriodException(500, "消毒鍋刪除失敗");
		}
		return ResultMsg.success("消毒鍋刪除成功").addData("");
	}

}
