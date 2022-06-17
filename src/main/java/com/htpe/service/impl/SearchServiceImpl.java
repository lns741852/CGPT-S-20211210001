package com.htpe.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrRequesition;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.SearchService;
import com.htpe.utils.ResultMsg;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;

	@Override
	public ResultMsg getBarcodeByAll(Integer pageNum, Integer pageSize, Map<String, Object> paramMap) {
		 	PageHelper.startPage(pageNum, pageSize);
			List<Map<String, Object>> barcodeList = csrBarcodeMapper.listBarcodeByAll(paramMap);
		    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(barcodeList);    
			return  ResultMsg.success("Barcode列表").addData(pageInfo);
	}

	@Override
	public ResultMsg getBarcodeBybarcode(String barcode) {
		return ResultMsg.success("barcode紀錄查詢").addData( csrBarcodeMapper.getbarcodeByname2(barcode));
	}

	@Override
	public ResultMsg getHistoryBybarocde(String barcode) {
		return ResultMsg.success("barcode紀錄查詢").addData( csrHistoryMapper.getHistoryBybarcode(barcode));
	}

}