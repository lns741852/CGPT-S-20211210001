package com.htpe.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.utils.ResultMsg;

public interface UseService {

	ResultMsg getBarcode(CsrRequesition csrRequesition);

	ResultMsg updateBarcode(List<CsrBarcode> csrBarcodes, HttpServletRequest request);

	ResultMsg getBarcode2(Map<String, Object> barcode);

	ResultMsg getReqByBarcode(Integer id);

	ResultMsg updateReqById(List<CsrBarcode> csrBarcodes, String roomno, HttpServletRequest request);


}
