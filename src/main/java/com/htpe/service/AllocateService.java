package com.htpe.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.utils.ResultMsg;

public interface AllocateService {

	ResultMsg listAllocate(String depno);

	ResultMsg removeAllocate(Integer id, HttpServletRequest request);

	ResultMsg getAllocate(Integer id);

	ResultMsg getbarcodeByname(String barcode);

	ResultMsg updateAllocate(CsrRequesition csrRequesition);

	ResultMsg updateBarcode(Integer id, List<CsrBarcode> csrBarcodes);

}
