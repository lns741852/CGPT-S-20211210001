package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrCasecar;

@Mapper
public interface CsrCasecarMapper {

	List<Map<String, Object>> listCasecar(Map<String, Object> paramMap);

	int insertCasecar(CsrCasecar casecar);

	@Insert("insert into CSR_CASECAR_SETDATA(CASECAR_ID,SETNO_ID,NUM)"
			+ " values(#{casecarno}, #{setno}, #{num})")
	int insertCasecarSetno(String casecarno, String setno,Integer num);
	
	CsrCasecar getCasecarById(Integer id);

	int updateCasecar(CsrCasecar casecar);

	@Delete("delete from CSR_CASECAR_SETDATA where CASECAR_ID=#{casecarno}")
	int deleteCasecarSetnoByNo(String casecarno);

	@Delete("delete from CSR_CASECAR where CASECARNO=#{casecarno}")
	int deleteCasecarByNo(String no);




} 