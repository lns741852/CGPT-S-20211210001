package com.htpe.mapper.old;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DataChange {
	
	@Select("select * from VGHTPETS.CSR_SETDATA_3M")
	List<Map<String, Object>> listSetData();
	
	@Select("select * from VGHTPETS.CSR_UDI")
	List<Map<String, Object>> listUDI();
	
	@Select("select * from VGHTPETS.CSR_SETDATA_SEQ_UDI")
	List<Map<String, Object>> listSetDataSeqUDI();
	
	@Select("select * from VGHTPETS.CSR_SETDATA_SEQ")
	List<Map<String, Object>> listSetDataSeq();
	
	@Select("select * from VGHTPETS.CSR_CASECARDATA")
	List<Map<String, Object>> listCasecarData();	
		
	@Select("select * from VGHTPETS.CSR_CASECAR")
	List<Map<String, Object>> listCasecar();	
	
	@Select("select * from VGHTPETS.CSR_SURGICAL")
	List<Map<String, Object>> listSurgical();
		
	@Select("select * from VGHTPETS.CSR_SURGICALLIST")
	List<Map<String, Object>> listSurgicallCasecar();
	
	@Select("select * from VGHTPETS.CSR_DEPNO")
	List<Map<String, Object>> listDepno();

	@Select("select * from VGHTPETS.CSR_POTDATA")
	List<Map<String, Object>> listPotdata();
	
	@Select("select * from VGHTPETS.CSR_PROCESS_DETAIL")
	List<Map<String, Object>> listPotdataProcess();

	@Select("select P.PROCESSNO ,P.PROCESSNAME ,P.DAY  , P.ISDELETE ,P.DATAUSERNO, P.DATAUSERNAME ,P.DATADATE , D.DATENAME "
			+ " from VGHTPETS.CSR_PROCESS as P inner join VGHTPETS.CSR_DATE as D on P.DAY=D.DAY")
	List<Map<String, Object>> listProcess();

	@Select("select * from VGHTPETS.CSR_SETDATA_POSITION")
	List<Map<String, Object>> listPosition();
	
	@Select("select * from VGHTPETS.CSR_POT_DETAIL")
	List<Map<String, Object>> listPotDepno();

	@Select("select * from VGHTPETS.CSR_PRINTER")
	List<Map<String, Object>> listPrinter();
	
}
