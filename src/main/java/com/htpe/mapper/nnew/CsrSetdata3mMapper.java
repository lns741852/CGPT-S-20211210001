package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.utils.ResultMsg;

@Mapper
public interface CsrSetdata3mMapper {
	
	
	@Select("select trim(DISINFECTION) as DISINFECTION, trim(POTNAME) as  POTNAME from CSR_POTDATA")
	List<Map<String, Object>> listPotData();

	List<Map<String, Object>> listSetno(Map<String, Object> paramMap);

	@Select("select X.PROCESSNO,X.PROCESSNAME,X.DATENAME,X.DAY from CSR_PROCESS as X "
			+ " inner join  CSR_PROCESS_POT as A on X.PROCESSNO=A.PROCESSNO"
			+ " inner join CSR_POTDATA as Y on A.DISINFECTION=Y.DISINFECTION"
			+ " where Y.DISINFECTION = #{potData}"
			+ " order by X.DAY")
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

	@Select("select trim(setno) setno from  CSR_SETDATA_3M")
	List<String>  getSetnoAll();

	@Update("update CSR_CASECAR_SETDATA set SETNO_ID=#{changeSetno} where SETNO_ID=#{setno}")
	void updateCasecarSetno(String setno, String changeSetno);

	@Delete("delete from CSR_CASECAR_SETDATA where SETNO_ID = #{setno}")
	void deleteCasecarSetno(CsrSetdata3m setno);
	
	
	
}