package com.htpe.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;


import com.htpe.bean.CsrBarcode;
import com.htpe.utils.ResultMsg;


public interface BarcodeService {

	ResultMsg saveBarcode(List<CsrBarcode> csrBarcodes, HttpServletRequest request);

	ResultMsg listReprint(Integer pageNum, Integer pageSize, String depno, String barcode, String setno);

	ResultMsg reprint(List<CsrBarcode> csrBarcodes, HttpServletRequest request);

}
