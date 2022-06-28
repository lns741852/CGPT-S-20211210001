package com.htpe.service;

import java.util.Map;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.Search;
import com.htpe.utils.ResultMsg;

public interface SearchService {

	ResultMsg getBarcodeByAll(Integer pageNum, Integer pageSize, Map<String, Object> paramMap);

	ResultMsg getBarcodeBybarcode(String barcode);

	ResultMsg getHistoryBybarocde(String barcode);

	ResultMsg listReqForSearch5(Search search);

	ResultMsg getReqById(Integer id);

	ResultMsg search3(String udi, String barcode);

	ResultMsg getPotWarehousing(Integer pageNum, Integer pageSize, CsrBarcode csrBarcode);

	ResultMsg listSearch6(String depno, String setno, String baseNum, String warehousingNum);

}
