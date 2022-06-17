package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.htpe.bean.CsrUdi;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface UDIService {

	ResultMsg listUDI(Integer typeId);

	ResultMsg saveUDI(CsrUdi csrUdi, HttpServletRequest request);

	ResultMsg getUDIById(Integer id);

	ResultMsg updateUDIById(CsrUdi csrUdi, Integer id, HttpServletRequest request) throws RequestPeriodException;

//	ResultMsg removeFile(Integer id, String filename) throws RequestPeriodException;

	ResultMsg removeUDI(Integer id) throws RequestPeriodException;

	ResultMsg getUDIByName(String udi);

}
