package com.htpe.service;

import java.util.List;

import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrRequesition;
import com.htpe.utils.ResultMsg;

public interface ReceiveService {

	ResultMsg updateBarcode(List<CsrBarcode> csrBarcodes);

	ResultMsg getBarcode(CsrRequesition csrRequesition);

}
