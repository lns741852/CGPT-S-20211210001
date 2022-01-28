package com.htpe.controller;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrAccount;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.AccountService;
import com.htpe.utils.ResultMsg;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;	
	
	/**
	 * �b���C��d��
	 */
	@GetMapping("/account")
	public ResultMsg  AccountList(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName,
			@RequestParam(value="empNO",required = false) String empNO) {
	    return accountService.listAccount(pageNum,pageSize,searchName,empNO);	  
	}
	/**
	 * ���u�����d��
	 */
	@GetMapping("/account/depno")
	public ResultMsg getDepNo(){			
		return accountService.listDepno();
	}
		
	/**
	 * �s�W�b��
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/account")		
	public ResultMsg DoAccountAdd(@Valid @RequestBody  CsrAccount csrAccount) throws RequestPeriodException{
		return accountService.saveAccount(csrAccount);
	}
		
	/**
	 * �s��b���d��
	 */
	@GetMapping("/account/{id}")
	public ResultMsg getAccountById(@PathVariable Integer id){			
		return accountService.getAccountById(id);
	}

	/**
	 * �ק�b��
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/account/{id}")		
	public ResultMsg DoAccountUpdate(@Valid @RequestBody  CsrAccount csrAccount,
				@PathVariable Integer id) throws RequestPeriodException{
			return accountService.updateAccount(csrAccount,id);
	}
	
	/**
	 * �R���b��
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/account/{id}")
	public ResultMsg removeAccount(@PathVariable Integer id) throws RequestPeriodException {
		return accountService.removeAccount(id);
	}


}
