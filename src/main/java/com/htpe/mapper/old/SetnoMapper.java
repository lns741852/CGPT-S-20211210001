package com.htpe.mapper.old;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrSetdata3m;

@Mapper
public interface SetnoMapper {

	List<Map<String, Object>> listSetno(Map<String, Object> paramMap);

	@Select("select trim(DISINFECTION) as DISINFECTION from VGHTPETS.CSR_POTDATA")
	List<String> getPotData();

	@Select("select trim(POT.DISINFECTION) as DISINFECTION,P.PROCESSNAME,P.DAY from VGHTPETS.CSR_Process as P "
			+ "inner join VGHTPETS.CSR_Process_Detail as PD on  P.PROCESSNO=PD.PROCESSNO "
			+ "inner join VGHTPETS.CSR_POTDATA as POT on POT.DISINFECTION=PD.DISINFECTION "
			+ "where POT.DISINFECTION=#{potData}")
	List<Map<String, Object>> getProcess(String potData);

	
	void addSetno(CsrSetdata3m csrSetdata3m);

	@Select("select count(id) from VGHTPETS.CSR_SETDATA_3M where  SETNO= #{setno}")
	int getCountBySetno(String setno);

}
