package com.htpe.service;

import java.util.Map;

import com.htpe.utils.ResultMsg;

public interface SearchService {

	ResultMsg getBarcodeByAll(Integer pageNum, Integer pageSize, Map<String, Object> paramMap);

	ResultMsg getBarcodeBybarcode(String barcode);

	ResultMsg getHistoryBybarocde(String barcode);

}
