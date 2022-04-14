package com.htpe.service;

import java.util.List;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.utils.ResultMsg;

public interface ReportService {

	ResultMsg listReport02(Integer pageNum, Integer pageSize, String start, String end);

	List<CsrSetdata3m> exportReport02(Integer pageNum, Integer pageSize, String start, String end);

}
