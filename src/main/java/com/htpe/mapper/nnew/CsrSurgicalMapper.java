package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrSurgical;

public interface CsrSurgicalMapper {
	
	List<Map<String, Object>> listSurgical(Map<String, Object> paramMap);

	@Select("select CASECARNO from CSR_CASECAR where CASECARNO =#{casecarNo}")
	List<String> getCasecarByNo(String casecarNo);

	int insertSurgical(CsrSurgical csrSurgical);

	@Insert("insert into CSR_SURGICAL_CASECAR(SURGICALNO,CASECARNO)"
			+ " values(#{surgicalno}, #{casecarno})")
	int insertSurgicalCasecar(String surgicalno, String casecarno);

	CsrSurgical getSurgicalById(Integer id);

	int updateSurgical(CsrSurgical csrSurgical);

	@Delete("delete from CSR_SURGICAL_CASECAR where SURGICALNO=#{surgicalno}")
	int deleteSurgicalCasecarByNo(String surgicalno);

	@Delete("delete from CSR_SURGICAL where SURGICALNO=#{no}")
	int deleteSurgical(String no);
	
}
