package com.htpe.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.htpe.bean.CsrUdiType;
import com.htpe.utils.ResultMsg;

public interface UDITypeService {

	ResultMsg listUDIType(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg getUDITypeById(Integer id);

	ResultMsg saveUDIType(CsrUdiType csrUdi, HttpServletRequest request, MultipartFile[] file);

	ResultMsg updateUDITypeById(CsrUdiType csrUdi, HttpServletRequest request, MultipartFile[] file);

}
