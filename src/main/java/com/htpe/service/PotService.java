package com.htpe.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.htpe.bean.CsrPoltld;
import com.htpe.utils.ResultMsg;

public interface PotService {

	ResultMsg getbarcodeByname(String barcode);

	ResultMsg savePot(List<CsrPoltld> csrPoltld, HttpServletRequest request);


}
