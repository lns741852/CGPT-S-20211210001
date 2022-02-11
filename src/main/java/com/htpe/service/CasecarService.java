package com.htpe.service;


import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrCasecar;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface CasecarService {

	ResultMsg listCasecar(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveCasecar(CsrCasecar casecar,HttpServletRequest request) throws RequestPeriodException;

	ResultMsg getCasecarByid(Integer id);

	ResultMsg updateCasecar(CsrCasecar csrCasecar, HttpServletRequest request, Integer id) throws RequestPeriodException;

	ResultMsg removeCasecar(String no) throws RequestPeriodException;

}
