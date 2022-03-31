package com.htpe.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


import com.htpe.bean.CsrSetdataPosition;
import com.htpe.utils.ResultMsg;


public interface PositionService {

	ResultMsg listPosition(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg savePosition(@Valid CsrSetdataPosition csrSetdataPosition, HttpServletRequest request);

	ResultMsg getPositionById(Integer id);

	ResultMsg updatePosition(@Valid CsrSetdataPosition csrSetdataPosition, Integer id, HttpServletRequest request);

	ResultMsg removePosition(Integer id);

}
