package com.htpe.mapper.nnew;

import java.util.List;

import com.htpe.bean.Report;
import com.htpe.bean.Report10;
import com.htpe.bean.Report6;
import com.htpe.bean.Report7;
import com.htpe.bean.Report8;
import com.htpe.bean.Report9;

public interface ReportMapper {

	List<Report6> listReport06(Report report);

	List<Report7> listReport07(Report report);

	List<Report8> listReport08(Report report);

	List<Report9>  listReport09(Report report);

	List<Report10> listReport10(Report report);

}
