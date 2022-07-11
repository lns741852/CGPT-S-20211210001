package com.htpe.mapper.nnew;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrPoltld;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.Report;
import com.htpe.utils.ResultMsg;

public interface CsrBarcodeMapper {

	List<CsrSetdata3m> timeoutSetno(@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	List<Map<String, Object>> getTimeoutNum(@Param("timeoutSetno")String timeoutSetno,@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	int insertPotdata(CsrBarcode barcode);

	int insertBarcode(CsrBarcode barcode);

	List<Map<String, Object>> listReprint(Map<String, Object> paramMap);

	CsrBarcode getbarcodeByname(String barcode);

	@Update("update CSR_BARCODE set STATUS='2',POTID=#{id}, LOCATION =#{depno} where barcode=#{barcode}")
	int updateBarcodeByName(String barcode, Integer id, String depno);

	List<CsrBarcode> getWarehousingList(CsrPoltld csrPoltld);

	int upadteWarehousing(Integer[] ids, String depno, String userno, String usercname, Date date);

	List<String> getBarcodeById(Integer[] ids);

	
	@Select("select Count(*) from CSR_BARCODE where CREATEDATETIME like current date ||'%'")
	int getTodayBarcode();

	int updateBarcodeById(CsrBarcode barcode);

	
	@Select("select * from CSR_BARCODE where RD_ID=#{rdId}")
	List<CsrBarcode>  getBarcodeByRdid(Integer rdId);

	@Select("select * from CSR_BARCODE where REQ_ID=#{reqId}")
	List<CsrBarcode> getBarcodeByReqId(Integer reqId);

	
	@Select("select * from CSR_BARCODE where barcode=#{barcode} and LOCATION=#{depno} and status='4' ")
	List<CsrBarcode> gebarcodeByNoForUse( Map<String, Object>  barcode);
	
	
	@Select("select * from CSR_BARCODE where REQ_ID=#{reqId} AND STATUS !='6'")
	List<CsrBarcode> getBarcodeByReqIdForReceive(Integer reqId);
	
	List<Map<String, Object>> listBarcodeByAll(Map<String, Object> paramMap);

	Map<String, Object> getbarcodeByname2(String barcode);

	CsrBarcode getbarcodeBynameForPot(String barcode);

	CsrBarcode getbarcodeBynameForAllocate(String barcode);

	List<Map<String, Object>> listBarcodeForSearch4(CsrBarcode csrBarcode);

	List<Integer> getCountByNO(String setno);

	List<CsrSetdata3m> listReport03(Report report);

	List<Map<String, Object>> getReport03Count(String setno, Report report);

	List<Map<String, Object>> getReport04Count(String setno, Report report);



}