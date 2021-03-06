package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrDepno;
import com.htpe.bean.CsrPotDepno;
import com.htpe.bean.CsrPotdata;
import com.htpe.bean.CsrPrinter;

public interface CsrDepnoMapper {

	List<Map<String, Object>> listDepno(Map<String, Object> paramMap);

	CsrDepno getDepnoById(Integer id);

	@Select("select count(*) from CSR_POT_DEPNO where  disinfection= #{disinfection} and depno=#{depno}")
	int countPotdata(String disinfection, String depno);

	int insertPotDepno(CsrPotDepno csrPotDepno);
	
	@Delete("delete from CSR_POT_DEPNO where ID = #{id}")
	int deletePotdata(Integer id);

	@Select("select count(*) from CSR_PRINTER where  PRINTERNAME= #{printername}  and depno=#{depno}")
	int countPrinter(String printername, String depno);

	int insertPrinter(@Valid CsrPrinter csrPrinter);

	@Delete("delete from CSR_PRINTER where ID = #{id}")
	int deletePrinter(Integer id);

	CsrDepno getDepnoByName(String name);
}