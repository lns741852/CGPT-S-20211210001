package com.htpe.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.htpe.bean.CsrPotdata;
import com.htpe.utils.ResultMsg;

public interface DisinfectionService {

	ResultMsg listDisinfection(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveDisinfection(CsrPotdata csrPotdata, HttpServletRequest request);

	ResultMsg listProcess();

	ResultMsg getPotdataById(Integer id);

	ResultMsg updatePotdata(CsrPotdata csrPotdata, HttpServletRequest request);

	ResultMsg removePotdata(String no);

}
