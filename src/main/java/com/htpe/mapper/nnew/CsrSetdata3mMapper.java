package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.utils.ResultMsg;

@Mapper
public interface CsrSetdata3mMapper {
	
	
	@Select("select trim(DISINFECTION) as DISINFECTION, trim(POTNAME) as  POTNAME from CSR_POTDATA")
	List<Map<String, Object>> listPotData();

	List<Map<String, Object>> listSetno(Map<String, Object> paramMap);

	@Select("select trim(PROCESSNO) as PROCESSNO, trim(PROCESSNAME) as  PROCESSNAME"
			+ ", trim(DAY) as  DAY, trim(DATENAME) as  DATENAME from CSR_PROCESS")
	List<Map<String, Object>> listProcess(String potData);

	@Select("select count(*) from CSR_SETDATA_3M where  SETNO= #{setno}")
	int countSetno(String setno);

	int insertSetno(CsrSetdata3m csrSetdata3m);

	@Select("select trim(CODE) as CODE, trim(NAME) as  NAME from CSR_SETDATA_POSITION")
	List<Map<String, Object>> listPosition();

	@Select("select trim(SETNO) as SETNO from CSR_SETDATA_3M where id= #{id}")
	String getSetnoColumnById(Integer id);

	int updateSetno(CsrSetdata3m csrSetdata3m);

	CsrSetdata3m getSetnoById(Integer id);

	@Delete("delete from CSR_SETDATA_3M where ID = #{id}")
	int deleteSetno(Integer id);

	@Select("select ID, SETNO, SETNAMECH from  CSR_SETDATA_3M where SETNO=#{setno}")
	CsrSetdata3m getSetnoByNo(String setno);
	
	
	
}