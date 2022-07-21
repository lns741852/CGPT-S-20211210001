package com.htpe.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.htpe.bean.CsrBox;
import com.htpe.utils.ResultMsg;

public interface BoxService {

	ResultMsg listBox(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveBox(@Valid CsrBox csrBox, HttpServletRequest request);

	ResultMsg getBoxById(Integer id);

	ResultMsg updateBox(@Valid CsrBox csrBox, Integer id, HttpServletRequest request);

	ResultMsg removeBox(Integer id);

	ResultMsg getBoxByNameAndStatus(String name);

	ResultMsg getBoxByName(String name);


}
