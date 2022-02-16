package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrProcess;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrProcessMapper;
import com.htpe.service.ProcessService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;


@Service
public class ProcessServiceImpl implements ProcessService{
	
	@Autowired
	CsrProcessMapper csrProcessMapper;

	@Override
	public ResultMsg listPackage(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> casecarList = csrProcessMapper.listProcess(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casecarList);    
		return  ResultMsg.success("包裝列表").addData(pageInfo);
	}

	@Override
	public ResultMsg saveProcess(CsrProcess csrProcess,HttpServletRequest request) throws RequestPeriodException {
		int count = csrProcessMapper.countProcess(csrProcess.getProcessno());
		if(count>=1) {			
			return ResultMsg.fail(401,"包裝已使用").addData("");
		}
		
		Date date = new Date();	
		csrProcess.setDatadate(date);
		csrProcess.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrProcess.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrProcess.setIsdelete("N");
			
		int num = csrProcessMapper.insertProcess(csrProcess);
		if(num<1) {
			throw new RequestPeriodException(500, "包裝加入失敗");
		}
		return ResultMsg.success("包裝成功加入").addData("");
	}

	@Override
	public ResultMsg getProcessById(Integer id) {
		return ResultMsg.success("包裝查詢").addData(csrProcessMapper.getProcessById(id));
	}

	@Override
	public ResultMsg updateProcess(CsrProcess csrProcess, Integer id, HttpServletRequest request) throws RequestPeriodException {
		int count = csrProcessMapper.countProcess(csrProcess.getProcessno());
		String changeSetno = csrProcess.getProcessno();
		String process = csrProcessMapper.getProcessColumnById(id);
		if((!changeSetno.equals(process)) && count >=1) {					
			return ResultMsg.fail(401,"包裝已使用").addData("");			
		}

		Date date = new Date();	
		csrProcess.setId(id);
		csrProcess.setDatadate(date);
		csrProcess.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrProcess.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrProcess.setIsdelete("N");
			
		int num = csrProcessMapper.updateProcess(csrProcess);
		if(num<1) {
			throw new RequestPeriodException(500, "包裝修改失敗");
		}
		return ResultMsg.success("包裝成功修改").addData("");
	}

	@Override
	public ResultMsg removeProcess(Integer id) throws RequestPeriodException {
		int count = csrProcessMapper.deleteProcess(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "包裝刪除失敗");
		}		
		return ResultMsg.success("包裝刪除成功").addData("");
	}
	


}
