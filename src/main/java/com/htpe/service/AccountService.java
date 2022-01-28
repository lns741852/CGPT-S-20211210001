package com.htpe.service;

import com.htpe.bean.CsrAccount;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface AccountService {

	
	ResultMsg listAccount(Integer pageNum, Integer pageSize,String searchName,String empNO);

	
	ResultMsg listDepno();

	
	ResultMsg saveAccount(CsrAccount csrAccount) throws RequestPeriodException;

	
	ResultMsg getAccountById(Integer id);

	
	ResultMsg updateAccount(CsrAccount csrAccount, Integer id) throws RequestPeriodException;

	
	ResultMsg removeAccount(Integer id) throws RequestPeriodException;
	
}
