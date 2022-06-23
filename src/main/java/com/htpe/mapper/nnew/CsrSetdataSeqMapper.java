package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.htpe.bean.CsrSetdataSeq;


@Mapper
public interface CsrSetdataSeqMapper {
	
	List<Map<String, Object>> listSeq(Map<String, Object> paramMap);

	
	@Select("select U.ID,U.CODE,U.CNAME,U.ENAME from CSR_UDI AS U"
			+ " left join CSR_SETDATA_SEQ_UDI AS SU on U.id=SU.CSR_UDI_ID"
			+ " where SU.CSR_UDI_ID is null"
			+ " Order by U.CODE")
	List<Map<String, Object>> listUnUDI();

	int insertSeq(CsrSetdataSeq seq);

	@Insert("insert into CSR_SETDATA_SEQ_UDI(CSR_SETDATA_SEQ_ID,CSR_UDI_ID) values(#{id}, #{UDIid})")
	int insertSeqUDI(Integer id, String UDIid);

	@Select("select ID,CODE,CNAME,ENAME from("
			+ " select U.ID,U.CODE,U.CNAME,U.ENAME from CSR_UDI AS U"
			+ " left join CSR_SETDATA_SEQ_UDI AS SU on U.id=SU.CSR_UDI_ID"
			+ " where SU.CSR_UDI_ID is null"
			+ " union all"
			+ " select U.ID,U.CODE,U.CNAME,U.ENAME from CSR_UDI AS U"
			+ " left join CSR_SETDATA_SEQ_UDI AS SU on U.id=SU.CSR_UDI_ID"
			+ " where SU.CSR_SETDATA_SEQ_ID=#{id})"
			+ " order by CODE")
	List<Map<String, Object>> listUDI(Integer id);


	CsrSetdataSeq getSeqById(Integer id);


	int updateSeq(CsrSetdataSeq seq);


	@Delete("delete from CSR_SETDATA_SEQ_UDI where CSR_SETDATA_SEQ_ID=#{id}")
	int deleteSeqUdiBySeqId(Integer id);

	@Delete("delete from CSR_SETDATA_SEQ where id=#{id}")
	int deleteSeq(Integer id);

	@Select("Select count(id) from CSR_SETDATA_SEQ_UDI where CSR_SETDATA_SEQ_ID=#{id}")
	int countSeqUdiById(Integer id);

	@Select("Select count(id) from CSR_SETDATA_SEQ where SETNO=#{setno} AND SEQ=#{seq} AND DEL_TYPE='N'")
	int countSeqBySnAndSetno(CsrSetdataSeq seq);

	List<CsrSetdataSeq> listSeqByType();

	@Update("update CSR_SETDATA_SEQ set PUBLIC_TYPE='N' where ID=#{seqId}")
	void updateSeqTypeById(String seqId);

	CsrSetdataSeq getSeqBySetnoAndSn(String setno, String setsn);

}

