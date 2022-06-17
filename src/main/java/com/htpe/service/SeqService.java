package com.htpe.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrSetdataSeq;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface SeqService {


	ResultMsg saveSeq(Map<String, Object> addUdi, HttpServletRequest request) throws RequestPeriodException;

	ResultMsg getSeq();
	
//	ResultMsg listSeq(Integer pageNum, Integer pageSize, String searchName);
//
//	ResultMsg listUnUDI();
//
//
//	ResultMsg getSeqById(Integer id);
//
//	ResultMsg listUDI(Integer id);
//
//	ResultMsg updateSeq(CsrSetdataSeq seq, String ids, HttpServletRequest request) throws RequestPeriodException;
//
//	ResultMsg removeSeq(Integer id) throws RequestPeriodException;

}
