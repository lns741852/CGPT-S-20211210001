package com.htpe.mapper.nnew;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrPoltld;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.utils.ResultMsg;

public interface CsrBarcodeMapper {

	List<CsrSetdata3m> timeoutSetno(@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	Map<String, Object> getTimeoutNum(@Param("timeoutSetno")String timeoutSetno,@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	int insertPotdata(CsrBarcode barcode);

	int insertBarcode(CsrBarcode barcode);

	List<Map<String, Object>> listReprint(Map<String, Object> paramMap);

	CsrBarcode getbarcodeByname(String barcode);

	@Update("update CSR_BARCODE set STATUS='2',POTID=#{id}, LOCATION =#{depno} where barcode=#{barcode}")
	int updateBarcodeByName(String barcode, Integer id, String depno);

	List<CsrBarcode> getWarehousingList(CsrPoltld csrPoltld);

}