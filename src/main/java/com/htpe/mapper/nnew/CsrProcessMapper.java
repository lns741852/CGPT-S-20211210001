package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrProcess;

public interface CsrProcessMapper {

	List<Map<String, Object>> listProcess(Map<String, Object> paramMap);

	@Select("select count(*) from CSR_Process where  PROCESSNO= #{processno}")
	int countProcess(String processno);

	int insertProcess(CsrProcess csrProcess);

	@Select("select ID,PROCESSNO,PROCESSNAME,DAY,DATENAME from CSR_Process where ID=#{id}")
	CsrProcess getProcessById(Integer id);

	@Select("select trim(PROCESSNO) as PROCESSNO from CSR_Process where id= #{id}")
	String getProcessColumnById(Integer id);

	int updateProcess(CsrProcess csrProcess);

	@Delete("delete from CSR_Process where ID = #{id}")
	int deleteProcess(Integer id);
	
	
}