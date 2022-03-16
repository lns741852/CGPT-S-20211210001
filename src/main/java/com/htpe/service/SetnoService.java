package com.htpe.service;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.exception.RequestPeriodException;
import com.htpe.utils.ResultMsg;

public interface SetnoService {


	ResultMsg listSetno(Integer pageNum, Integer pageSize, String searchName);


	ResultMsg listPotData();


	ResultMsg listProcess(String potdata);


	ResultMsg saveSetno(CsrSetdata3m csrSetdata3m) throws RequestPeriodException;


	ResultMsg listPosition();


	ResultMsg updateSetno(CsrSetdata3m csrSetdata3m,Integer id) throws RequestPeriodException;


	ResultMsg getSetnoById(Integer id);


	ResultMsg removeSetno(Integer id) throws RequestPeriodException;


	ResultMsg getSetnoByNo(String setno);


	ResultMsg getSetnoAll();

}
