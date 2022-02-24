package com.htpe.mapper.nnew;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrPotdata;



@Mapper
public interface CsrPotdataMapper {

	@Select("select id, trim(disinfection) disinfection, trim(potname) potname from  CSR_POTDATA")
	List<Map<String, Object>> listPotdata(Map<String, Object> paramMap);

	int insertPotdata(CsrPotdata csrPotdata);

	@Insert("insert into CSR_PROCESS_POT(DISINFECTION,PROCESSNO)"
			+ " values(#{disinfection}, #{processno})")
	int insertPotdataProcess(String disinfection, String processno);

	CsrPotdata getPotdataById(Integer id);

	int updatePotdata(CsrPotdata csrPotdata);

	@Delete("delete from CSR_PROCESS_POT where DISINFECTION=#{disinfection}")
	int deletePotdataProcessByNo(String disinfection);

	@Delete("delete from CSR_POTDATA where DISINFECTION=#{no}")
	int deletePotdata(String no);


}