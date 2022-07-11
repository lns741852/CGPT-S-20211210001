package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrPoltld;
import com.htpe.utils.ResultMsg;

public interface IndicatorService {

	ResultMsg getIndicatorByAll(CsrPoltld csrPoltld);

	ResultMsg updateIndicator(CsrPoltld csrPoltld, HttpServletRequest request);

}
