package com.htpe.service;

import javax.validation.Valid;

import com.htpe.bean.CsrPotDepno;
import com.htpe.bean.CsrPrinter;
import com.htpe.utils.ResultMsg;

public interface DepnoService {

	ResultMsg listDepno(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg getDepnoById(Integer id);

	ResultMsg savePotdata(CsrPotDepno csrPotDepno);

	ResultMsg removePotdata(Integer id);

	ResultMsg savePrinter(@Valid CsrPrinter csrPrinter);

	ResultMsg removePrinter(Integer id);

	ResultMsg getDepnoByName(String name);

}
