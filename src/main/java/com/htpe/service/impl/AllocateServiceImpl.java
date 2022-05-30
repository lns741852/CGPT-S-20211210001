package com.htpe.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrRequesition;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.AllocateService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class AllocateServiceImpl  implements AllocateService{
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;

	@Override
	public ResultMsg listAllocate(String depno) {
		return ResultMsg.success("申領確認列表").addData(csrRequesitionMapper.getReqByDepno(depno));
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

}
