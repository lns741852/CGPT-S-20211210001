package com.htpe.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrReqdetail;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.Search;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrReqdetailMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.mapper.nnew.CsrSearchMapper;
import com.htpe.mapper.nnew.CsrSetdata3mMapper;
import com.htpe.mapper.nnew.CsrUdiMapper;
import com.htpe.service.SearchService;
import com.htpe.utils.DateUtils;
import com.htpe.utils.ResultMsg;

@Service
public class SearchServiceImpl implements SearchService {
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;
	
	@Autowired
	CsrSearchMapper csrSearchMapper;
	
	@Autowired
	CsrReqdetailMapper csrReqdetailMapper;
	
	@Autowired
	CsrUdiMapper csrUdiMapper;
	
	@Autowired
	CsrSetdata3mMapper csrSetdata3mMapper;

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

	@Override
	public ResultMsg listReqForSearch5(Search search) {	 
		return ResultMsg.success("交易紀錄查詢").addData( csrRequesitionMapper.listReqForSearch5(search));
	}

	@Override
	public ResultMsg getReqById(Integer id) {
		
		Map<String, Object> map = new HashedMap<String, Object>();
		
		CsrRequesition reqById = csrRequesitionMapper.getReqById(id);
		List<CsrReqdetail> rdByReqId = csrReqdetailMapper.getRdByReqId(id);
		List<CsrBarcode> barcodeByReqId = csrBarcodeMapper.getBarcodeByReqId(id);
		
		
		map.put("req", reqById);
		map.put("reqDetail", rdByReqId);
		map.put("barcode", barcodeByReqId);
		
		return ResultMsg.success("單位申請單列表").addData(map);
		
		
	}

	@Override
	public ResultMsg search3(String udi, String barcode) {
		return ResultMsg.success("UDI查詢").addData(	csrUdiMapper.getUDIByNameForSearch(udi));
	}

	@Override
	public ResultMsg getPotWarehousing(Integer pageNum, Integer pageSize, CsrBarcode csrBarcode) {	
	 	PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> barcodeList = csrBarcodeMapper.listBarcodeForSearch4(csrBarcode);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(barcodeList);    
		return  ResultMsg.success("Barcode列表").addData(pageInfo);
	}


	@Override
	public ResultMsg listSearch6(String depno, String setno, String baseNum, String warehousingNum) {
		
		List<Map<String, Object>> list = csrSetdata3mMapper.listSetno2(depno,setno);
							
		for(Map<String, Object> map : list) {
			
			map.put("tagNum", 0);
			map.put("potNum", 0);
			map.put("warehousingNum", 0);
			map.put("reNum",0);
			
			List<Integer> countByNO = csrBarcodeMapper.getCountByNO((String)map.get("SETNO"));
			int tagNum=0;
			int potNum=0;
			int warehousingNum1=0;
			
			int baseNum1 = (int) map.get("BASENUM");
					
			for (int i=0; i< countByNO.size(); i++) {
				
				if(i==0) {
					map.put("tagNum", countByNO.get(0));
					tagNum = countByNO.get(0);
					
				}
				if(i==1) {
					map.put("potNum", countByNO.get(1));	
					potNum = countByNO.get(1);
				}
				if(i==2) {
					map.put("warehousingNum", countByNO.get(2));
					warehousingNum1 = countByNO.get(2);
					
				}
				
				map.put("reNum", baseNum1-tagNum-potNum-warehousingNum1);
		}
				
		
	}
		Set<Integer> set = new HashSet<Integer>();
		int index =0;
		if(baseNum != null && baseNum.equals("1")) {
			for(Map<String, Object> map : list) {			
				if((int) map.get("BASENUM") != 0) {
					set.add(index);
				}
			index++;
			}
		}
		index=0;
		if(warehousingNum != null && warehousingNum.equals("1")) {
			for(Map<String, Object> map : list) {
				if(!map.isEmpty()) {
					if(!map.get("warehousingNum").equals(0) ) {
						set.add(index);
					}			
				}
				index++;
			}
		}
		
		int j= 0;
		for(int i : set) {
			if(j != 0) {
				i--;
			}
			list.remove(i);
			j++;
		}
		
		
	return ResultMsg.success("點班作業報表").addData(list);
	}
}
