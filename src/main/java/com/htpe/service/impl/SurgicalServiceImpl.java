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
import com.htpe.bean.CsrSurgical;
import com.htpe.bean.CsrSurgicalCasecar;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrSurgicalMapper;
import com.htpe.service.SurgicalService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class SurgicalServiceImpl implements SurgicalService{
	
	@Autowired
	CsrSurgicalMapper csrSurgicalMapper;

	@Override
	public ResultMsg listSurgical(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> casecarList = csrSurgicalMapper.listSurgical(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casecarList);    
		return  ResultMsg.success("手術列表").addData(pageInfo);
	}
	

	@Override
	public ResultMsg getCasecarByNo(String casecarNo) {
		List<String> casecar = csrSurgicalMapper.getCasecarByNo(casecarNo);
		if(casecar.size()<1) {
			return ResultMsg.fail(403,"無個案車").addData("");
		}
		return ResultMsg.success("個案車代號").addData(casecar.get(0).trim());
	}


	@Override
	public ResultMsg saveSurgical(CsrSurgical csrSurgical, HttpServletRequest request)throws RequestPeriodException {
		Date date = new Date();	
		csrSurgical.setDatadate(date);
		csrSurgical.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrSurgical.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrSurgical.setIsdelete("N");
		
		int num = csrSurgicalMapper.insertSurgical(csrSurgical);
		if(num < 1) {
			throw new RequestPeriodException(500, "手術新增失敗");
		}
		
		List<CsrSurgicalCasecar> casecars = csrSurgical.getCsrSurgicalCasecar();
		if(casecars != null) {
			for (CsrSurgicalCasecar casecar : casecars) {
				int num2 =  csrSurgicalMapper.insertSurgicalCasecar(csrSurgical.getSurgicalno(), casecar.getCasecarno());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "手術新增失敗");
				}
			}
		}
		
		return ResultMsg.success("手術新增成功").addData("");
	}


	@Override
	public ResultMsg getSurgicalById(Integer id) {
		return ResultMsg.success("手術").addData(csrSurgicalMapper.getSurgicalById(id));
	}


	@Override
	public ResultMsg updateSurgical(CsrSurgical csrSurgical, HttpServletRequest request) throws RequestPeriodException {
		Date date = new Date();	
		csrSurgical.setDatadate(date);
		csrSurgical.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrSurgical.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrSurgical.setIsdelete("N");
		
		int num = csrSurgicalMapper.updateSurgical(csrSurgical);
		if(num < 1) {
			throw new RequestPeriodException(500, "手術修改失敗");
		}
		
		int num3 = csrSurgicalMapper.deleteSurgicalCasecarByNo(csrSurgical.getSurgicalno());
//		if(num3 < 1) {
//			throw new RequestPeriodException(500, "手術修改失敗");
//		}
		
		List<CsrSurgicalCasecar> casecars = csrSurgical.getCsrSurgicalCasecar();
		if(casecars != null) {
			for (CsrSurgicalCasecar casecar : casecars) {
				int num2 =  csrSurgicalMapper.insertSurgicalCasecar(csrSurgical.getSurgicalno(), casecar.getCasecarno());
				if(num2 < 1) {
					throw new RequestPeriodException(500, "手術修改失敗");
				}
			}
		}
		
		return ResultMsg.success("手術修改成功").addData("");
	}


	@Override
	public ResultMsg removeSurgical(String no) throws RequestPeriodException {
		int count = csrSurgicalMapper.deleteSurgical(no);
		if(count < 1) {
			throw new RequestPeriodException(500, "手術刪除失敗");
		}
		csrSurgicalMapper.deleteSurgicalCasecarByNo(no);

		return ResultMsg.success("手術刪除成功").addData("");
	}




}
