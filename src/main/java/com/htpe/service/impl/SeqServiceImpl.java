package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrSetdataSeq;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrSetdataSeqMapper;
import com.htpe.service.SeqService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class SeqServiceImpl implements SeqService {
	
	@Autowired
	CsrSetdataSeqMapper csrSetdataSeqMapper;

	@Override
	public ResultMsg listSeq(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> seqList = csrSetdataSeqMapper.listSeq(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(seqList);    
		return  ResultMsg.success("�L�]�Ǹ��C��").addData(pageInfo);
	}

	@Override
	public ResultMsg listUnUDI() {
		return  ResultMsg.success("���ϥ�UDI").addData(csrSetdataSeqMapper.listUnUDI());
	}

	@Override
	public ResultMsg saveSeq(CsrSetdataSeq seq, String ids,HttpServletRequest request) throws RequestPeriodException {

		Date date = new Date();		
		seq.setDatadate(date);
		seq.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));

		int num = csrSetdataSeqMapper.insertSeq(seq);
		if(num < 1) {
			throw new RequestPeriodException(500, "�L�]�s�W����");
		}
		if(!ids.isEmpty()) {
			String[] idsList = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //�h�����ƭ�
			for (String UDIid : idsList) {
				int num2 =  csrSetdataSeqMapper.insertSeqUDI(seq.getId(),UDIid);
				if(num2 < 1) {
					throw new RequestPeriodException(500, "�b�s�W����");
				}
			}
		}
		return ResultMsg.success("�b���s�W���\").addData("");
	}

	@Override
	public ResultMsg getSeqById(Integer id) {
		return ResultMsg.success("�w�ϥ�UDI").addData(csrSetdataSeqMapper.getSeqById(id));
	}

	@Override
	public ResultMsg listUDI(Integer id) {
		return  ResultMsg.success("�i�ϥ�UDI").addData(csrSetdataSeqMapper.listUDI(id));
	}

	@Override
	public ResultMsg updateSeq(CsrSetdataSeq seq, String ids, HttpServletRequest request) throws RequestPeriodException {
		Date date = new Date();		
		seq.setDatadate(date);
		seq.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));

		int num = csrSetdataSeqMapper.updateSeq(seq);
		if(num < 1) {
			throw new RequestPeriodException(500, "�L�]�ק異��");
		}
		int num2 = csrSetdataSeqMapper.deleteSeqUdiBySeqId(seq.getId());
		if(num2 < 1) {
			throw new RequestPeriodException(500, "�b���ק異��");
		}
		
		if(!ids.isEmpty()) {
		String[] idsList = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //�h�����ƭ�
			for (String UDIid : idsList) {
				int num3 =  csrSetdataSeqMapper.insertSeqUDI(seq.getId(),UDIid);
				if(num3 < 1) {
					throw new RequestPeriodException(500, "�ק異��");
				}
			}
		}
		return ResultMsg.success("�ק令�\").addData("");
	}

	@Override
	public ResultMsg removeSeq(Integer id) throws RequestPeriodException {
		int count = csrSetdataSeqMapper.deleteSeq(id);
		if(count < 1) {
			throw new RequestPeriodException(500, "�L�]�R������");
		}
		int num2 = csrSetdataSeqMapper.deleteSeqUdiBySeqId(id);
		if(num2 < 1) {
			throw new RequestPeriodException(500, "�L�]�R������");
		}
		return ResultMsg.success("�L�]�R�����\").addData("");
	}
	

}
