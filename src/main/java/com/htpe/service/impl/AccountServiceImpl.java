package com.htpe.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrAccount;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrAccountMapper;
import com.htpe.service.AccountService;
import com.htpe.utils.ResultMsg;


@Service
public class AccountServiceImpl  implements AccountService{
		
	@Autowired
	CsrAccountMapper csrAccountMapper;
	
	
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
	public ResultMsg saveAccount(CsrAccount csrAccount) throws RequestPeriodException {
		int count = csrAccountMapper.countUserno(csrAccount.getUserno());
		if(count >= 1) {	
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		Date date = new Date();		
		//csrAccount.setCreatedatetime(df.format(date));
		csrAccount.setIscancel("N");
		int num = csrAccountMapper.insertAccount(csrAccount);
		if(num < 1) {
			throw new RequestPeriodException(401, "帳號修改失敗");
		}
		return ResultMsg.success("帳號新增成功").addData("");

	}

	
	@Override
	public ResultMsg getAccountById(Integer id) {		
		return ResultMsg.success("員工查詢").addData(csrAccountMapper.getAccountById(id));
	}

	
	@Override
	public ResultMsg updateAccount(CsrAccount csrAccount, Integer id) throws RequestPeriodException {
		int count = csrAccountMapper.countUserno(csrAccount.getUserno());
		String changeEmpno = csrAccount.getUserno();
		String empno = csrAccountMapper.getEmpNoById(id);
		if((!changeEmpno.equals(empno)) && count >=1) {					
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		csrAccount.setId(id);
		int num = csrAccountMapper.updateAccount(csrAccount);
		if(num < 1) {
			throw new RequestPeriodException(500, "帳號修改失敗");
		}
		return ResultMsg.success("帳號修改成功").addData("");
	}

	
	@Override
	public ResultMsg removeAccount(Integer id) throws RequestPeriodException {
		int count = csrAccountMapper.deleteAccount(id);
		if(count < 1) {
			throw new RequestPeriodException(500, "帳號刪除失敗");
		}
		return ResultMsg.success("帳號刪除成功").addData("");
	}


	
}
