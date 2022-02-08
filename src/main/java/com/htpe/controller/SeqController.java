package com.htpe.controller;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	 * �L�]���@�C��d��
	 */
	@GetMapping("/seq")
	public ResultMsg  listSeq(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return seqService.listSeq(pageNum,pageSize,searchName);	  
	}

	/**
	 * ���ϥξ���d��
	 */
	@GetMapping("/seq/unused/udi")
	public ResultMsg listUnUDI(){			
		return seqService.listUnUDI();
	}
		
	
	/**
	 * �s�W���@�L�]
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/seq")
	public ResultMsg saveSeq(@RequestBody Map<String, Object> map,HttpServletRequest request) throws RequestPeriodException{
		
		if(((String)map.get("setno")).isEmpty()) {
			throw new RequestPeriodException(303, "�N�����o����");
		}
		if(((String)map.get("setnamech")).isEmpty()) {
			throw new RequestPeriodException(303, "�W�٤��o����");
		}
		if(((String)map.get("seq")).isEmpty()) {
			throw new RequestPeriodException(303, "�Ǹ����o����");
		}
		
		CsrSetdataSeq seq = new CsrSetdataSeq();
		seq.setSetno((String)map.get("setno"));
		seq.setSetnamech((String)map.get("setnamech"));
		seq.setSeq((String)map.get("seq"));
		
		return seqService.saveSeq(seq,(String)map.get("ids"),request);
	}
	
	/**
	 * �i�ϥξ���d��
	 */
	@GetMapping("/seq/used/udi/{id}")
	public ResultMsg listUDI(@PathVariable Integer id){			
		return seqService.listUDI(id);
	}
		
	/**
	 * �L�]�d��
	 */
	@GetMapping("/seq/{id}")
	public ResultMsg getSeqById(@PathVariable Integer id){			
		return seqService.getSeqById(id);	
	}
	
	/**
	 * �L�]��s
	 */
	@PutMapping("/seq/{id}")
	public ResultMsg updateSeq(@RequestBody Map<String, String> map,
				@PathVariable Integer id,
				HttpServletRequest request) throws RequestPeriodException{
		if(((String)map.get("setno")).isEmpty()) {
			throw new RequestPeriodException(303, "�N�����o����");
		}
		if(((String)map.get("setnamech")).isEmpty()) {
			throw new RequestPeriodException(303, "�W�٤��o����");
		}
		if(((String)map.get("seq")).isEmpty()) {
			throw new RequestPeriodException(303, "�Ǹ����o����");
		}
		CsrSetdataSeq seq = new CsrSetdataSeq();
		seq.setId(id);
		seq.setSetno((String)map.get("setno"));
		seq.setSetnamech((String)map.get("setnamech"));				
		return seqService.updateSeq(seq,(String)map.get("ids"),request);
	}
	
	/**
	 * �R���b��
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/seq/{id}")
	public ResultMsg removeSeq(@PathVariable Integer id) throws RequestPeriodException {
		return seqService.removeSeq(id);
	}
}
