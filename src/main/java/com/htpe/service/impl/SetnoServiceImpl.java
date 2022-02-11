package com.htpe.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrSetdata3mMapper;
import com.htpe.service.SetnoService;
import com.htpe.utils.ResultMsg;


@Service
public class SetnoServiceImpl implements SetnoService{
	
	@Autowired
	CsrSetdata3mMapper csrSetdata3mMapper;

	
	@Override
	public ResultMsg listSetno(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> setnoList = csrSetdata3mMapper.listSetno(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(setnoList);    
		return  ResultMsg.success("盤包列表").addData(pageInfo);
	}

	
	@Override
	public ResultMsg listPotData() {
		List<Map<String, Object>> potData = csrSetdata3mMapper.listPotData();
		return ResultMsg.success("消毒鍋查詢").addData(potData);
	}

	
	@Override
	public ResultMsg listProcess(String potdata) {
		List<Map<String, Object>> listpotdata = csrSetdata3mMapper.listProcess(potdata);
		return ResultMsg.success("消毒包查詢").addData(listpotdata);
	}


	
	@Override
	public ResultMsg saveSetno(CsrSetdata3m csrSetdata3m) throws RequestPeriodException {
		int count = csrSetdata3mMapper.countSetno(csrSetdata3m.getSetno());
		if(count>=1) {			
			return ResultMsg.fail(500,"盤包已使用").addData("");
		}
		if(csrSetdata3m.getSetno().substring(0).equals("W"));{
			csrSetdata3m.setSettype("W");
		}
		csrSetdata3m.setIsdelete("N");
		int num = csrSetdata3mMapper.insertSetno(csrSetdata3m);
		if(num<1) {
			throw new RequestPeriodException(500, "盤包加入失敗");
		}
		return ResultMsg.success("盤包成功加入").addData("");
		
	}

	
	@Override
	public ResultMsg listPosition() {
		List<Map<String, Object>> position = csrSetdata3mMapper.listPosition();
		return ResultMsg.success("儲位查詢").addData(position);
	}

	
	@Override
	public ResultMsg updateSetno(CsrSetdata3m csrSetdata3m, Integer id) throws RequestPeriodException {
		int count = csrSetdata3mMapper.countSetno(csrSetdata3m.getSetno());
		String changeSetno = csrSetdata3m.getSetno();
		String setno = csrSetdata3mMapper.getSetnoColumnById(id);
		if((!changeSetno.equals(setno)) && count >=1) {					
			return ResultMsg.fail(500,"盤包已使用").addData("");			
		}
		csrSetdata3m.setId(id);
		int num = csrSetdata3mMapper.updateSetno(csrSetdata3m);		
		if(num<1) {
			throw new RequestPeriodException(500, "盤包修改失敗");
		}
		return ResultMsg.success("盤包修改成功").addData("");

	}

	
	@Override
	public ResultMsg getSetnoById(Integer id) {
		return ResultMsg.success("盤包查詢").addData(csrSetdata3mMapper.getSetnoById(id));
	}

	
	@Override
	public ResultMsg removeSetno(Integer id) throws RequestPeriodException {
		int count = csrSetdata3mMapper.deleteSetno(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "盤包刪除失敗");
		}		
		return ResultMsg.success("帳號刪除成功").addData("");
	}


	@Override
	public ResultMsg getSetnoByNo(String setno) {		 
		return ResultMsg.success("盤包查詢").addData(csrSetdata3mMapper.getSetnoByNo(setno));
	}

}
