package com.htpe.mapper.nnew;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrPoltld;

public interface CsrPoltldMapper {

	int insertPot(CsrPoltld pot);

	@Select("select * from CSR_POLTLD where DEPNO=#{depno} AND POTNAME=#{potname} AND POTNO=#{potno} AND POTSN=#{potsn} AND "
			+ " POTSCANTIME like '%'||(#{date})||'%'")
	CsrPoltld selectPot(String depno, String potname, String potno, Integer potsn, String date);

	CsrPoltld getPot(CsrPoltld csrPoltld);

}