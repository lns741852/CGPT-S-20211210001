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
import com.htpe.bean.CsrBox;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBoxMapper;
import com.htpe.service.BoxService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class BoxServiceImpl  implements BoxService{
	
	@Autowired
	CsrBoxMapper csrBoxMapper;

	@Override
	public ResultMsg listBox(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> casecarList = csrBoxMapper.listBox(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casecarList);    
		return  ResultMsg.success("器械盒列表").addData(pageInfo);
	}

	@Override
	public ResultMsg saveBox(CsrBox csrBox,HttpServletRequest request) throws RequestPeriodException {
		int count = csrBoxMapper.countBox(csrBox.getUdi());
		if(count>=1) {			
			return ResultMsg.fail(401,"器械盒已使用").addData("");
		}
		
		Date date = new Date();	
		csrBox.setDatadate(date);
		csrBox.setStatus("1");
		csrBox.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		
		int num = csrBoxMapper.insertBox(csrBox);
		if(num<1) {
			throw new RequestPeriodException(500, "器械盒加入失敗");
		}
		return ResultMsg.success("器械盒成功加入").addData("");
	}

	@Override
	public ResultMsg getBoxById(Integer id) {
		return ResultMsg.success("器械盒查詢").addData(csrBoxMapper.getBoxById(id));
	}

	@Override
	public ResultMsg updateBox(CsrBox csrBox, Integer id, HttpServletRequest request) throws RequestPeriodException {
		int count = csrBoxMapper.countBox(csrBox.getUdi());
		String changeSetno = csrBox.getUdi();
		String Box = csrBoxMapper.getBoxColumnById(id);
		if((!changeSetno.equals(Box)) && count >=1) {					
			return ResultMsg.fail(401,"器械盒已使用").addData("");			
		}

		Date date = new Date();	
		csrBox.setId(id);
		csrBox.setDatadate(date);
		csrBox.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));

			
		int num = csrBoxMapper.updateBox(csrBox);
		if(num<1) {
			throw new RequestPeriodException(500, "器械盒修改失敗");
		}
		return ResultMsg.success("器械盒成功修改").addData("");
	}

	@Override
	public ResultMsg removeBox(Integer id) throws RequestPeriodException {
		int count = csrBoxMapper.deleteBox(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "器械盒刪除失敗");
		}		
		return ResultMsg.success("器械盒刪除成功").addData("");
	}
	


}
