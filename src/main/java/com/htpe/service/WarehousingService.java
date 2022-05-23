package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrPoltld;
import com.htpe.utils.ResultMsg;

public interface WarehousingService {

	ResultMsg getWarehousingList(CsrPoltld csrPoltld);

	ResultMsg upadteWarehousing(Integer[] ids, String depno, String usercname, HttpServletRequest request);

}
