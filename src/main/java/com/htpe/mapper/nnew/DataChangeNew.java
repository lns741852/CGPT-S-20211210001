package com.htpe.mapper.nnew;


import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataChangeNew {

	@Insert("insert into CSR_SETDATA_3M (SETNO, SETNAMECH, SETNAME, SETTYPE, ISDELETE, PRICE, NUMBASE_CSR, NUMBASE_CSR2, NUMBASE_OR1, NUMBASE_OR2, NUMBASE_OR3, NUMBASE_WR1, SPOTNO, SPROCESS, SDAY, SPRINTNO)"
			+ "values (#{SETNO}, #{SETNAMECH}, #{SETNAME}, #{SETTYPE}, #{ISDELETE}, #{PRICE}, #{NUMBASE_CSR}, #{NUMBASE_CSR2}, #{NUMBASE_OR1}, #{NUMBASE_OR2}, #{NUMBASE_OR3}, #{NUMBASE_WR1}, #{SPOTNO}, #{SPROCESS}, #{SDAY}, #{SPRINTNO})")
	public void insertSetNo(Map<String, Object> map);
	
	
	
	@Insert("insert into CSR_UDI (CODE,CNAME,ENAME,DESC,PICPATH1,PICPATH2,PICPATH3,FILEPATH1,FILEPATH2,FILEPATH3,PUBLIC_TYPE,UPD_TYPE,DEL_TYPE,DATAUSER,DATADATE)"
			+ "values (#{CODE}, #{CNAME}, #{ENAME}, #{DESC}, #{PICPATH1}, #{PICPATH2}, #{PICPATH3}, #{FILEPATH1}, #{FILEPATH2}, #{FILEPATH3}, #{PUBLIC_TYPE}, #{UPD_TYPE}, #{DEL_TYPE}, #{DATAUSER}, #{DATADATE})")
	public void insertUDI(Map<String, Object> map);
	
	
	@Insert("insert into CSR_SETDATA_SEQ_UDI (CSR_SETDATA_SEQ_ID, CSR_UDI_ID)"
			+ "values (#{CSR_SETDATA_SEQ_ID}, #{CSR_UDI_ID})")
	public void insertSeqUDI(Map<String, Object> map);
	
	
	@Insert("insert into CSR_SETDATA_SEQ (SETNO,SETNAMECH,SEQ,PUBLIC_TYPE,UPD_TYPE,DEL_TYPE,DATAUSER,DATADATE)"
			+ "values (#{SETNO}, #{SETNAMECH}, #{SEQ}, #{PUBLIC_TYPE}, #{UPD_TYPE}, #{DEL_TYPE}, #{DATAUSER}, #{DATADATE})")
	public void insertSeq(Map<String, Object> map);

	
	@Insert("insert into CSR_CASECAR_SETDATA (CASECAR_ID, SETNO_ID, NUM)"
			+ "values (#{CASECAR_ID}, #{SETNO_ID}, #{NUM})")
	public void insertCasecarSetdata(Map<String, Object> map);
	
	@Insert("insert into CSR_CASECAR (CASECARNO, DATAUSERNO, DATAUSERNAME, DATADATE, ISDELETE)"
			+ "values (#{CASECARNO}, #{DATAUSERNO}, #{DATAUSERNAME}, #{DATADATE}, #{ISDELETE})")
	public void insertCasecar(Map<String, Object> map);
	
	
	@Insert("insert into CSR_SURGICAL (SURGICALNO, SURGICALNAME, SURGICALNAMECH, ISDELETE, DATAUSERNO, DATAUSERNAME, DATADATE)"
			+ "values (#{SURGICALNO}, #{SURGICALNAME}, #{SURGICALNAMECH}, #{ISDELETE}, #{DATAUSERNO}, #{DATAUSERNAME}, #{DATADATE})")
	public void insertSurgical(Map<String, Object> map);
	
	@Insert("insert into CSR_SURGICAL_CASECAR (SURGICALNO, CASECARNO)"
			+ "values (#{SURGICALNO}, #{CASECARNO})")
	public void insertCarSurgical(Map<String, Object> map);


	@Insert("insert into CSR_DEPNO (DEPNO,DEPNAME,DEPNAMECH,ISDELETE,DATAUSERNO,DATAUSERNAME,DATADATE,TYPE)"
			+ "values (#{DEPNO}, #{DEPNAME}, #{DEPNAMECH}, #{ISDELETE}, #{DATAUSERNO}, #{DATAUSERNAME}, #{DATADATE}, #{TYPE})")
	public void insertDepno(Map<String, Object> map3);


	@Insert("insert into CSR_POTDATA (DISINFECTION,POTNAME,ISDELETE,DATAUSERNO,DATAUSERNAME,DATADATE)"
			+ "values (#{DISINFECTION}, #{POTNAME}, #{ISDELETE}, #{DATAUSERNO}, #{DATAUSERNAME}, #{DATADATE})")
	public void insertPotdata(Map<String, Object> map3);


	@Insert("insert into CSR_PROCESS (PROCESSNO,PROCESSNAME,DAY,DATENAME,ISDELETE,DATAUSERNO,DATAUSERNAME,DATADATE)"
			+ "values (#{PROCESSNO}, #{PROCESSNAME}, #{DAY}, #{DATENAME}, #{ISDELETE}, #{DATAUSERNO}, #{DATAUSERNAME}, #{DATADATE})")
	public void insertProcess(Map<String, Object> map3);


	@Insert("insert into CSR_SETDATA_POSITION (CODE,NAME,PUBLIC_TYPE,UPD_TYPE,DEL_TYPE,DATAUSER,DATADATE)"
			+ "values (#{CODE}, #{NAME}, #{PUBLIC_TYPE}, #{UPD_TYPE}, #{DEL_TYPE}, #{DATAUSER}, #{DATADATE})")
	public void insertPosition(Map<String, Object> map3);
}