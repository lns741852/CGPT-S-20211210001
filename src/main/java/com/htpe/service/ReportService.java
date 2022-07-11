package com.htpe.service;

import java.util.List;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.Report;
import com.htpe.bean.Report5;
import com.htpe.utils.ResultMsg;

public interface ReportService {

	ResultMsg listReport02(Integer pageNum, Integer pageSize, String start, String end);

	List<CsrSetdata3m> exportReport02(Integer pageNum, Integer pageSize, String start, String end);

	ResultMsg listReport03(Report report);

	List<CsrSetdata3m> exportReport03(Report report);

	ResultMsg listReport04(Report report);

	List<CsrSetdata3m> exportReport04(Report report);

	ResultMsg listReport05(Report report);
	
	List<Report5> exportReport05(Report report);

}
