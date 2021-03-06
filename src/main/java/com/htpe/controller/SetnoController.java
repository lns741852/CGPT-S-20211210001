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

import com.htpe.bean.CsrSetdata3m;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.SetnoService;
import com.htpe.utils.ResultMsg;

@RestController
public class SetnoController {
	
	@Autowired
	SetnoService setnoService;
	
	/**
	 * 盤包列表查詢
	 */
	@GetMapping("/setno")
	public ResultMsg  listSetno(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "20") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return   setnoService.listSetno(pageNum,pageSize,searchName);
	}
	
	/**
	 * 消毒鍋列表查詢
	 */
	@GetMapping("/setno/potdata")
	public ResultMsg listPotData(){			
			return setnoService.listPotData();
	}
	
	/**
	 * 消毒鍋查詢
	 */
	@GetMapping("/setno/potdata/{potdata}")
	public ResultMsg getProcess(@PathVariable String potdata){			
			return setnoService.listProcess(potdata);
	}
	
	/**
	 * 儲位
	 */
	@GetMapping("/setno/position")
	public ResultMsg listPosition(){			
			return setnoService.listPosition();
	}
		
	/**
	 * 盤包新增
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/setno")		
	public ResultMsg saveSetno(@Valid @RequestBody  CsrSetdata3m csrSetdata3m) throws RequestPeriodException{			
		return setnoService.saveSetno(csrSetdata3m);
	}
	
	/**
	 * 盤包單一查詢
	 */
	@GetMapping("/setno/{id}")
	public ResultMsg getSetno(@PathVariable Integer id){			
		return setnoService.getSetnoById(id);
	}
	
	
	/**
	 * 盤包修改
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/setno/{id}")		
	public ResultMsg updateSetno(@Valid @RequestBody  CsrSetdata3m csrSetdata3m, 
			@PathVariable Integer id) throws RequestPeriodException{			
	return setnoService.updateSetno(csrSetdata3m,id);
	}
	
	
	/**
	 * 盤包刪除
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/setno/{id}")
	public ResultMsg removeSetno(@PathVariable Integer id) throws RequestPeriodException {
		return setnoService.removeSetno(id);
	}

}
