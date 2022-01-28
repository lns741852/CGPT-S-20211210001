package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.htpe.bean.CsrUdi;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface UDIService {

	ResultMsg listUDI(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveUDI(CsrUdi csrUdi, HttpServletRequest request, MultipartFile pic1, MultipartFile pic2,
			MultipartFile pic3, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws RequestPeriodException;

	ResultMsg getUDIById(Integer id);

	ResultMsg updateUDIById(CsrUdi csrUdi, Integer id, HttpServletRequest request, MultipartFile pic1,
			MultipartFile pic2, MultipartFile pic3, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws RequestPeriodException;

	ResultMsg removeFile(Integer id, String filename) throws RequestPeriodException;

	ResultMsg removeUDI(Integer id) throws RequestPeriodException;

}
