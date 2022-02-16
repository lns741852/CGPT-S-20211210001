package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrProcess;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface ProcessService {

	ResultMsg listPackage(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveProcess(CsrProcess csrProcess, HttpServletRequest request) throws RequestPeriodException;

	ResultMsg getProcessById(Integer id);

	ResultMsg updateProcess(CsrProcess csrProcess, Integer id, HttpServletRequest request) throws RequestPeriodException;

	ResultMsg removeProcess(Integer id) throws RequestPeriodException;

}
