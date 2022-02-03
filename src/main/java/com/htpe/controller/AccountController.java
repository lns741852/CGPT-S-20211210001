package com.htpe.controller;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResultMsg saveAccount(@RequestBody Map<String, String> map) throws RequestPeriodException{
		
		for (Entry<String, String> m : map.entrySet()) {
			if(m.getValue().equals("")) {
				throw new RequestPeriodException(303, "�Ȥ��o����");
			}
		}
		
		CsrAccount csrAccount = new CsrAccount();
		csrAccount.setUserno(map.get("userno"));
		csrAccount.setUsercname(map.get("usercname"));
		csrAccount.setDepno(map.get("depno"));
		csrAccount.setUserpwd(map.get("userpwd"));
		csrAccount.setSystemprivilege(map.get("systemprivilege"));
		
		return accountService.saveAccount(csrAccount,map.get("oneids"),map.get("twoids"));
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
	public ResultMsg DoAccountUpdate(@RequestBody Map<String, String> map,
				@PathVariable Integer id) throws RequestPeriodException{
		
		CsrAccount csrAccount = new CsrAccount();
		csrAccount.setId(id);
		csrAccount.setUserno(map.get("userno"));
		csrAccount.setUsercname(map.get("usercname"));
		csrAccount.setDepno(map.get("depno"));
		csrAccount.setUserpwd(map.get("userpwd"));
		csrAccount.setSystemprivilege(map.get("systemprivilege"));
		
		return accountService.updateAccount(csrAccount,map.get("oneids"),map.get("twoids"));
	}
	
	/**
	 * �R���b��
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/account/{id}")
	public ResultMsg removeAccount(@PathVariable Integer id) throws RequestPeriodException {
		return accountService.removeAccount(id);
	}
	
	/**
	 * all�v���d��
	 * @throws RequestPeriodException 
	 */
	@GetMapping("/auth")
	public ResultMsg listAuth(){
		return accountService.listAuth();
	}
	


}
