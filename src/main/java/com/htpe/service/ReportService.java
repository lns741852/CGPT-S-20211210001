package com.htpe.service;

import java.util.List;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.Report;
import com.htpe.bean.Report5;
import com.htpe.bean.Report6;
import com.htpe.bean.Report7;
import com.htpe.bean.Report8;
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

	ResultMsg listReport06(Report report);

	List<Report6> exportReport06(Report report);

	ResultMsg listReport07(Report report);

	List<Report7> exportReport07(Report report);

	ResultMsg listReport08(Report report);

	List<Report8> exportReport08(Report report);



}
