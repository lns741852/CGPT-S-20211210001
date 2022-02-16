package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrSurgical;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface SurgicalService {

	ResultMsg listSurgical(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveSurgical(CsrSurgical csrSurgical, HttpServletRequest request) throws RequestPeriodException;

	ResultMsg getCasecarByNo(String casecarNo);

	ResultMsg getSurgicalById(Integer id);

	ResultMsg updateSurgical(CsrSurgical csrSurgical, HttpServletRequest request) throws RequestPeriodException;

	ResultMsg removeSurgical(String no) throws RequestPeriodException;

}
