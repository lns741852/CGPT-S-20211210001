package com.htpe.service;

import com.htpe.bean.CsrRequesition;
import com.htpe.utils.ResultMsg;

public interface ApplyService {

	ResultMsg listCostcenter();

	ResultMsg getCasecerByno(String casecarno);

	ResultMsg saveApply(CsrRequesition csrRequesition);

	ResultMsg getReqPrint(Integer reqId);

	ResultMsg saveRelocate(CsrRequesition csrRequesition);

}
