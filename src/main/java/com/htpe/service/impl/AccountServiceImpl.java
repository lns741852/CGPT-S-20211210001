package com.htpe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrAccount;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrAccountMapper;
import com.htpe.mapper.nnew.CsrMenuMapper;
import com.htpe.service.AccountService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;


@Service
public class AccountServiceImpl  implements AccountService{
		
	@Autowired
	CsrAccountMapper csrAccountMapper;
	
	@Autowired
	CsrMenuMapper csrMenuMapper;
	
	@Override
	public ResultMsg listAccount( Integer pageNum, Integer pageSize,String searchName, String empNO) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    paramMap.put("empNO", empNO);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> accountList = csrAccountMapper.listAccount(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(accountList);    
		return  ResultMsg.success("帳號列表").addData(pageInfo);
	}

	
	@Override
	public ResultMsg listDepno() {
		int count = csrAccountMapper.countDepno();		
		return  ResultMsg.success("員工部門").addData(csrAccountMapper.listDepno(count));
	}

	
	@Override
	public ResultMsg saveAccount(CsrAccount csrAccount,String ids) throws RequestPeriodException {
		int count = csrAccountMapper.countUserno(csrAccount.getUserno());
		if(count >= 1) {	
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		Date date = new Date();		
		csrAccount.setCreatetime(date);
		csrAccount.setIscancel("N");
		int num = csrAccountMapper.insertAccount(csrAccount);
		if(num < 1) {
			throw new RequestPeriodException(401, "帳新增改失敗");
		}
		
		//String authId = oneIds + "," + twoIds; //1,1,1,2,2,3,4,4
		String[] authIds = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //去除重複值
		for (String authorityId : authIds) {
			int num2 =  csrAccountMapper.insertAccountAuth(csrAccount.getId(),authorityId);
			if(num2 < 1) {
				throw new RequestPeriodException(401, "帳新增失敗");
			}
		}
		return ResultMsg.success("帳號新增成功").addData("");

	}

	
	@Override
	public ResultMsg getAccountById(Integer id) {		
		return ResultMsg.success("員工查詢").addData(csrAccountMapper.getAccountById(id));
	}

	
	@Override
	public ResultMsg updateAccount(CsrAccount csrAccount,String ids) throws RequestPeriodException {
		int count = csrAccountMapper.countUserno(csrAccount.getUserno());
		String changeEmpno = csrAccount.getUserno();
		String empno = csrAccountMapper.getUserNoById(csrAccount.getId());
		if((!changeEmpno.equals(empno)) && count >=1) {					
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		int num = csrAccountMapper.updateAccount(csrAccount);
		if(num < 1) {
			throw new RequestPeriodException(500, "帳號修改失敗");
		}
		
		int num2 = csrAccountMapper.deleteAuth(csrAccount.getId());
		if(num2 < 1) {
			throw new RequestPeriodException(500, "帳號修改失敗");
		}
		
		//String authId = oneIds + "," + twoIds; //1,1,1,2,2,3,4,4
		String[] authIds = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //去除重複值
		for (String authorityId : authIds) {
			int num3 =  csrAccountMapper.insertAccountAuth(csrAccount.getId(),authorityId);
			if(num3 < 1) {
				throw new RequestPeriodException(401, "帳號修改失敗");
			}
		}		
		return ResultMsg.success("帳號修改成功").addData("");
	}

	
	@Override
	public ResultMsg removeAccount(Integer id) throws RequestPeriodException {
		int count = csrAccountMapper.deleteAccount(id);
		if(count < 1) {
			throw new RequestPeriodException(500, "帳號刪除失敗");
		}
		int num2 = csrAccountMapper.deleteAuth(id);
		if(num2 < 1) {
			throw new RequestPeriodException(500, "帳號修改失敗");
		}
		return ResultMsg.success("帳號刪除成功").addData("");
	}


	@Override
	public ResultMsg listAuth() {
		return ResultMsg.success("所有權限").addData(csrMenuMapper.listAuth());
	}


	@Override
	public ResultMsg userInfo(String name) {
		return ResultMsg.success("使用者info").addData(csrAccountMapper.getAccountByUserno(name));
	}

	
}
