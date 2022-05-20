package com.htpe.service;

import com.htpe.bean.CsrPoltld;
import com.htpe.utils.ResultMsg;

public interface IndicatorService {

	ResultMsg getIndicatorByAll(CsrPoltld csrPoltld);

	ResultMsg updateIndicator(CsrPoltld csrPoltld);

}
