package com.htpe.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlInlineBinaryData;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.htpe.bean.CsrSetdataSeq;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.SeqService;
import com.htpe.utils.ResultMsg;

@RestController
public class SeqController {
	
	@Autowired
	SeqService seqService;
	
	
	
	/**
	 * 盤包維護列表查詢
	 */
	@PostMapping("/setno/seq")
	public ResultMsg  saveSeq(@RequestBody Map<String, Object> addUdi,HttpServletRequest httpServletRequest) {

	    return seqService.saveSeq(addUdi,httpServletRequest);	  
	}
	
	
	/**
	 * 盤包維護列表查詢
	 */
	@GetMapping("/setno/seq")
	public ResultMsg  listSeq(Integer id, Integer[] udiIds) {
		for(Integer i : udiIds) {
			System.out.println(i);
		}
	    return null;	  
	}
	
//	/**
//	 * 盤包維護列表查詢
//	 */
//	@GetMapping("/seq")
//	public ResultMsg  listSeq(
//			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
//            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
//			@RequestParam(value="searchName", required = false) String searchName) {
//	    return seqService.listSeq(pageNum,pageSize,searchName);	  
//	}
//
//	/**
//	 * 未使用器械查詢
//	 */
//	@GetMapping("/seq/un/udi")
//	public ResultMsg listUnUDI(){			
//		return seqService.listUnUDI();
//	}
//		
//	
//	/**
//	 * 新增維護盤包
//	 * @throws RequestPeriodException 
//	 */
//	@PostMapping("/seq")
//	public ResultMsg saveSeq(@RequestBody Map<String, Object> map,HttpServletRequest request) throws RequestPeriodException{
//		
//		if(((String)map.get("setno")).isEmpty()) {
//			throw new RequestPeriodException(303, "代號不得為空");
//		}
//		if(((String)map.get("setnamech")).isEmpty()) {
//			throw new RequestPeriodException(303, "名稱不得為空");
//		}
//		if(((String)map.get("seq")).isEmpty()) {
//			throw new RequestPeriodException(303, "序號不得為空");
//		}
//		
//		CsrSetdataSeq seq = new CsrSetdataSeq();
//		seq.setSetno((String)map.get("setno"));
//		seq.setSetnamech((String)map.get("setnamech"));
//		seq.setSeq((String)map.get("seq"));
//		
//		return seqService.saveSeq(seq,(String)map.get("ids"),request);
//	}
//	
//	/**
//	 * 可使用器械查詢
//	 */
//	@GetMapping("/seq/used/udi/{id}")
//	public ResultMsg listUDI(@PathVariable Integer id){			
//		return seqService.listUDI(id);
//	}
//		
//	/**
//	 * 盤包查詢
//	 */
//	@GetMapping("/seq/{id}")
//	public ResultMsg getSeqById(@PathVariable Integer id){			
//		return seqService.getSeqById(id);	
//	}
//	
//	/**
//	 * 盤包更新
//	 */
//	@PutMapping("/seq/{id}")
//	public ResultMsg updateSeq(@RequestBody Map<String, String> map,
//				@PathVariable Integer id,
//				HttpServletRequest request) throws RequestPeriodException{
//		if(((String)map.get("setno")).isEmpty()) {
//			throw new RequestPeriodException(303, "代號不得為空");
//		}
//		if(((String)map.get("setnamech")).isEmpty()) {
//			throw new RequestPeriodException(303, "名稱不得為空");
//		}
//		if(((String)map.get("seq")).isEmpty()) {
//			throw new RequestPeriodException(303, "序號不得為空");
//		}
//		CsrSetdataSeq seq = new CsrSetdataSeq();
//		seq.setId(id);
//		seq.setSetno((String)map.get("setno"));
//		seq.setSetnamech((String)map.get("setnamech"));				
//		return seqService.updateSeq(seq,(String)map.get("ids"),request);
//	}
//	
//	/**
//	 * 刪除帳號
//	 * @throws RequestPeriodException 
//	 */
//	@DeleteMapping("/seq/{id}")
//	public ResultMsg removeSeq(@PathVariable Integer id) throws RequestPeriodException {
//		return seqService.removeSeq(id);
//	}
}
