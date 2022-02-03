package com.htpe.service;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;

import com.htpe.bean.CsrAccount;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface AccountService {

	
	ResultMsg listAccount(Integer pageNum, Integer pageSize,String searchName,String empNO);

	
	ResultMsg listDepno();

	
	ResultMsg saveAccount(CsrAccount csrAccount,String oneIds, String twoIds) throws RequestPeriodException;

	
	ResultMsg getAccountById(Integer id);

	
	ResultMsg updateAccount(CsrAccount csrAccount,String oneIds, String twoIds) throws RequestPeriodException;

	
	ResultMsg removeAccount(Integer id) throws RequestPeriodException;


	ResultMsg listAuth();
	
}
