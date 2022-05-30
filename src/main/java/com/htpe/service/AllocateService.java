package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.utils.ResultMsg;

public interface AllocateService {

	ResultMsg listAllocate(String depno);

	ResultMsg removeAllocate(Integer id, HttpServletRequest request);

}
