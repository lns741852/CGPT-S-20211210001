package com.htpe.controller;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin(origins = "http://127.0.0.1", maxAge = 3600)
@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;	
	
	/**
	 * 帳號列表查詢
	 */
	@GetMapping("/account")
	public ResultMsg  listAccount(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName,
			@RequestParam(value="empNO",required = false) String depNo) {
	    return accountService.listAccount(pageNum,pageSize,searchName,depNo);	  
	}
	/**
	 * 員工部門查詢
	 */
	@GetMapping("/account/depno")
	public ResultMsg listDepNo(){			
		return accountService.listDepno();
	}
		
	/**
	 * 新增帳號
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/account")
	public ResultMsg saveAccount(@RequestBody Map<String, Object> map) throws RequestPeriodException{
		
		for (Entry<String, Object> m : map.entrySet()) {
			if(m.getValue().equals("")) {
				throw new RequestPeriodException(303, "值不得為空");
			}
		}
		
		CsrAccount csrAccount = new CsrAccount();
		csrAccount.setUserno((String)map.get("userno"));
		csrAccount.setUsercname((String)map.get("usercname"));
		csrAccount.setDepno((String)map.get("depno"));
		csrAccount.setUserpwd((String)map.get("userpwd"));
		csrAccount.setSystemprivilege((String)map.get("systemprivilege"));
		
		return accountService.saveAccount(csrAccount,(String)map.get("ids"));
	}
		
	/**
	 * 編輯帳號查詢
	 */
	@GetMapping("/account/{id}")
	public ResultMsg getAccountById(@PathVariable Integer id){			
		return accountService.getAccountById(id);
	}

	/**
	 * 修改帳號
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/account/{id}")		
	public ResultMsg updateAccount(@RequestBody Map<String, Object> map,
				@PathVariable Integer id) throws RequestPeriodException{
		
		CsrAccount csrAccount = new CsrAccount();
		csrAccount.setId(id);
		csrAccount.setUserno((String)map.get("userno"));
		csrAccount.setUsercname((String)map.get("usercname"));
		csrAccount.setDepno((String)map.get("depno"));
		csrAccount.setUserpwd((String)map.get("userpwd"));
		csrAccount.setSystemprivilege((String)map.get("systemprivilege"));
		
		return accountService.updateAccount(csrAccount,(String)map.get("ids"));
	}
	
	/**
	 * 刪除帳號
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/account/{id}")
	public ResultMsg removeAccount(@PathVariable Integer id) throws RequestPeriodException {
		return accountService.removeAccount(id);
	}
	
	/**
	 * all權限查詢
	 * @throws RequestPeriodException 
	 */
	@GetMapping("/auth")
	public ResultMsg listAuth(){
		return accountService.listAuth();
	}
	
	
	
	/**
	 * 使用者info
	 * @throws RequestPeriodException 
	 */
	@GetMapping("/info/{name}")
	public ResultMsg userInfo(@PathVariable String name){
		return accountService.userInfo(name);
	}


}
