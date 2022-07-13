package com.htpe.mapper.nnew;

import java.util.List;

import com.htpe.bean.Report;
import com.htpe.bean.Report6;
import com.htpe.bean.Report7;

public interface ReportMapper {

	List<Report6> listReport06(Report report);

	List<Report7> listReport07(Report report);

}
