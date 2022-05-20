package com.htpe.mapper.nnew;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrPoltld;

public interface CsrPoltldMapper {

	int insertPot(CsrPoltld pot);

	@Select("select * from CSR_POLTLD where DEPNO=#{depno} AND POTNAME=#{potname} AND POTNO=#{potno} AND POTSN=#{potsn} AND "
			+ " POTSCANTIME like '%'||(#{date})||'%'")
	CsrPoltld selectPot(String depno, String potname, String potno, Integer potsn, String date);

	CsrPoltld getPot(CsrPoltld csrPoltld);

	int updateIndicator(CsrPoltld csrPoltld);
	
	
	@Select("select MAX(POTSN) as max  from CSR_POLTLD "
			+ "where POTNO =#{potname} "
			+ "AND  POTSCANTIME like (select distinct to_char(CURRENT DATE,'yyyy-MM-dd') from CSR_POLTLD)  ||'%'")
	int getPotsnByName(String potname);
	
	
	
	@Select("select count(*) from CSR_POLTLD "
			+ "where POTNO =#{potname} "
			+ "AND  POTSCANTIME like (select distinct to_char(CURRENT DATE,'yyyy-MM-dd') from CSR_POLTLD)  ||'%'")
	int getPotsnCount(String potname);
	
	

}