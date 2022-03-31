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
import com.htpe.bean.CsrSetdataPosition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrSetdataPositionMapper;
import com.htpe.service.PositionService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class PositionServiceImpl implements PositionService {
	
	@Autowired
	CsrSetdataPositionMapper csrSetdataPositionMapper;

	@Override
	public ResultMsg listPosition(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> setnoList = csrSetdataPositionMapper.listPosition(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(setnoList);    
		return  ResultMsg.success("儲位列表").addData(pageInfo);
	}

	@Override
	public ResultMsg savePosition(@Valid CsrSetdataPosition csrSetdataPosition,HttpServletRequest request) {
		int count = csrSetdataPositionMapper.countPosition(csrSetdataPosition.getCode());
		if(count>=1) {			
			return ResultMsg.fail(401,"儲位已使用").addData("");
		}

		Date date = new Date();	
		csrSetdataPosition.setDatadate(date);
		csrSetdataPosition.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrSetdataPosition.setDelType("N");
		csrSetdataPosition.setUpdType("N");
		csrSetdataPosition.setPublicType("Y");
		
		
		int num = csrSetdataPositionMapper.insertPosition(csrSetdataPosition);
		if(num<1) {
			throw new RequestPeriodException(500, "儲位加入失敗");
		}
		return ResultMsg.success("儲位成功加入").addData("");
	}

	@Override
	public ResultMsg getPositionById(Integer id) {
		return ResultMsg.success("儲位查詢").addData(csrSetdataPositionMapper.getPositionById(id));
	}

	@Override
	public ResultMsg updatePosition(@Valid CsrSetdataPosition csrSetdataPosition, Integer id,HttpServletRequest request) {
		int count = csrSetdataPositionMapper.countPosition(csrSetdataPosition.getCode());
		String changePosition = csrSetdataPosition.getCode();
		String position = csrSetdataPositionMapper.getPositionColumnById(id);
		if((!changePosition.equals(position)) && count >=1) {					
			return ResultMsg.fail(401,"儲位已使用").addData("");			
		}

		Date date = new Date();	
		csrSetdataPosition.setId(id);
		csrSetdataPosition.setDatadate(date);
		csrSetdataPosition.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrSetdataPosition.setDelType("N");
		csrSetdataPosition.setUpdType("Y");
		csrSetdataPosition.setPublicType("Y");
			
		int num = csrSetdataPositionMapper.updatePosition(csrSetdataPosition);
		if(num<1) {
			throw new RequestPeriodException(500, "儲位修改失敗");
		}
		return ResultMsg.success("儲位成功修改").addData("");
	}

	@Override
	public ResultMsg removePosition(Integer id) {
		int count = csrSetdataPositionMapper.deletePosition(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "儲位刪除失敗");
		}		
		return ResultMsg.success("儲位刪除成功").addData("");
	}

}
