package com.htpe.mapper.nnew;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.htpe.bean.CsrSetdata3m;

public interface CsrBarcodeMapper {

	List<CsrSetdata3m> timeoutSetno(@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);

	Map<String, Object> getTimeoutNum(@Param("timeoutSetno")String timeoutSetno,@Param("start")String start, @Param("end")String end, @Param("todayEnd")String todayEnd);
}