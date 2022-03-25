package com.htpe.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrPotDepno;
import com.htpe.bean.CsrPotdata;
import com.htpe.bean.CsrPrinter;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrDepnoMapper;
import com.htpe.service.DepnoService;
import com.htpe.utils.ResultMsg;

@Service
public class DepnoServiceImpl  implements DepnoService{
	
	@Autowired
	CsrDepnoMapper csrDepnoMapper;
	
	@Override
	public ResultMsg listDepno(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> casecarList = csrDepnoMapper.listDepno(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casecarList);    
		return  ResultMsg.success("部門列表").addData(pageInfo);
	}

	@Override
	public ResultMsg getDepnoById(Integer id) {
		return ResultMsg.success("部門資料").addData(csrDepnoMapper.getDepnoById(id));
	}

	@Override
	public ResultMsg savePotdata(CsrPotDepno csrPotDepno) {
		int count = csrDepnoMapper.countPotdata(csrPotDepno.getDisinfection(),csrPotDepno.getDepno());
		if(count>=1) {			
			return ResultMsg.fail(401,"代號已使用").addData("");
		}
		
		int num = csrDepnoMapper.insertPotDepno(csrPotDepno);
		if(num<1) {
			throw new RequestPeriodException(500, "消毒鍋加入失敗");
		}
		return ResultMsg.success("消毒鍋成功加入").addData("");
	}

	@Override
	public ResultMsg removePotdata(Integer id) {
		int count = csrDepnoMapper.deletePotdata(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "消毒鍋刪除失敗");
		}		
		return ResultMsg.success("消毒鍋刪除成功").addData("");
	}

	@Override
	public ResultMsg savePrinter(@Valid CsrPrinter csrPrinter) {
		int count = csrDepnoMapper.countPrinter(csrPrinter.getPrintername(),csrPrinter.getDepno());
		if(count>=1) {			
			return ResultMsg.fail(401,"代號已使用").addData("");
		}
		
		int num = csrDepnoMapper.insertPrinter(csrPrinter);
		if(num<1) {
			throw new RequestPeriodException(500, "印表機加入失敗");
		}
		return ResultMsg.success("印表機成功加入").addData("");
	}


	@Override
	public ResultMsg removePrinter(Integer id) {
		int count = csrDepnoMapper.deletePrinter(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "印表機刪除失敗");
		}		
		return ResultMsg.success("印表機刪除成功").addData("");
	}
	

}
