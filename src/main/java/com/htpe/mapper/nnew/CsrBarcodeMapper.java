package com.htpe.mapper.nnew;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.utils.ResultMsg;

public interface CsrBarcodeMapper {

	List<CsrSetdata3m> timeoutSetno(@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	Map<String, Object> getTimeoutNum(@Param("timeoutSetno")String timeoutSetno,@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	int insertPotdata(CsrBarcode barcode);

	int insertBarcode(CsrBarcode barcode);

	List<Map<String, Object>> listReprint(Map<String, Object> paramMap);

	CsrBarcode getbarcodeByname(String barcode);
}