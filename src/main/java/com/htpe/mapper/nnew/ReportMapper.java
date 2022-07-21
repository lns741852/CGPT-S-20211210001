package com.htpe.mapper.nnew;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.Report;
import com.htpe.bean.Report10;
import com.htpe.bean.Report11;
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

	@Select("SELECT cu.UDI AS UDI,ca.USERCNAME AS username, cu.UPDATE_TIME AS updateTime  FROM CSR_UDI cu "
			+ "INNER JOIN "
			+ "CSR_ACCOUNT ca "
			+ "ON "
			+ "cu.UPDATE_ID =ca.USERNO "
			+ "WHERE "
			+ "	STATUS='2'")
	List<Report11> listReport11();

	@Select("SELECT cub.UDI AS UDI ,cub.DATADATE AS updateTime ,ca.USERCNAME  AS username  FROM CSR_UDI_BOX cub  "
			+ "INNER JOIN "
			+ "CSR_ACCOUNT ca "
			+ "ON "
			+ "cub.DATAUSER  =ca.USERNO  "
			+ "WHERE "
			+ "	STATUS='2'")
	List<Report11> listReport11_2();

}
