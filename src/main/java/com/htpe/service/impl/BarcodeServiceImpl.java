package com.htpe.service.impl;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.BarcodeNum;
import com.htpe.bean.CsrBarcode;
import com.htpe.bean.CsrBox;
import com.htpe.bean.CsrHistory;
import com.htpe.bean.CsrSetdata3m;
import com.htpe.bean.CsrSetdataSeq;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrBarcodeMapper;
import com.htpe.mapper.nnew.CsrBoxMapper;
import com.htpe.mapper.nnew.CsrHistoryMapper;
import com.htpe.mapper.nnew.CsrSetdata3mMapper;
import com.htpe.mapper.nnew.CsrSetdataSeqMapper;
import com.htpe.mapper.nnew.CsrSetdataSeqUdiMapper;
import com.htpe.service.BarcodeService;
import com.htpe.utils.ResultMsg;

@Service
public class BarcodeServiceImpl implements BarcodeService{
	
	@Autowired
	CsrBarcodeMapper csrBarcodeMapper;
	
	@Autowired
	CsrHistoryMapper csrHistoryMapper;
	
	@Autowired
	CsrBoxMapper csrBoxMapper;
	
	@Autowired
	CsrSetdata3mMapper csrSetdata3m;
	
	@Autowired
	CsrSetdataSeqMapper csrSetdataSeqMapper;

	@Override
	public ResultMsg saveBarcode(List<CsrBarcode> csrBarcodes, HttpServletRequest request) {
		
				csrBarcodes.forEach(e ->{
					csrSetdataSeqMapper.updateSeqTypeById(e.getSeqId());
				});
	
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
		        try {
		            // Constructs a FileWriter object given a file name.
		            FileWriter fw = null;
		            for(CsrBarcode barcode : csrBarcodes) {
		            	
		            	
		            	if(barcode.getBox() == "") {			            	
			            	if(fw ==null) {
			            		fw = new FileWriter("D://Printer//TEST.txt");
			            	}
		            	}
		            		            	      
		            	int numBarcode = BarcodeNum.printNum();	    
		            	if(numBarcode == 1) {  
		            		numBarcode = csrBarcodeMapper.getTodayBarcode();
		        			numBarcode += 1;
		            	}
		            	
		            	String  numString =String.valueOf(numBarcode);		            
		            	while (numString.length()<4) {
		            		numString = "0"+numString ;
						}
		            	
		            	if(barcode.getBox() != "") {
		            		numString = "";
		            	}
		            	
		            	
		            	barcode.setBarcode(barcode.getBarcode()+numString);		            	
		            	barcode.setCreatedatetime(date);
		            	barcode.setSetsn(barcode.getSetsn());
		            	barcode.setStatus("1");
		            	barcode.setReqId(0);
		            	barcode.setRdId(0);
		            	barcode.setPotid(0);
		            	barcode.setChecking("N");
		            	barcode.setUsing("N");
		            	barcode.setReceive("N");
		            	barcode.setLocation(barcode.getDepno());
		            	barcode.setWearhousing("N");
		            	barcode.setDepnocre(barcode.getDepno());
		            	barcode.setDpotsn(0);
		            	
		        		int num = csrBarcodeMapper.insertBarcode(barcode);
		        		if(num < 1) {
		        			throw new RequestPeriodException(500, "標間製作失敗");
		        		}
		            			            
		            	CsrHistory history = new CsrHistory();		            	
		            	history.setReqId(0);
		            	history.setBarcode(barcode.getBarcode());
		            	history.setDutyno(barcode.getDatauserno());
		            	history.setDutyname(barcode.getDatausername());
		            	history.setUsertime(barcode.getCreatedatetime());
		            	history.setSn(1);
		            	history.setIslast("Y");
		            	history.setDepno(barcode.getDepno());
		            	history.setIsused("N");
		            	history.setAction("W");
		            	history.setLocation(barcode.getLocation());
		            	history.setPotsn(0);
		            	history.setPotid(0);
		            	
		        		int num2 = csrHistoryMapper.insertHistory(history);
		        		if(num2 < 1) {
		        			throw new RequestPeriodException(500, "標間製作失敗");
		        		}

		        		CsrBox box = csrBoxMapper.getBoxByNameAndStatus(barcode.getBarcode());
		        		if(box != null) {	        		
			        		CsrBox csrBox = new CsrBox();
			        		csrBox.setId(box.getId());
			        		csrBox.setStatus("2");
			        		csrBoxMapper.updateBox(csrBox);		        			
		        		}

	        		
		        		if(barcode.getBox() == "") {	
			        		String dateString = dateFormat.format(barcode.getEffectivedate());
			        		   		            	
			            	if(barcode.getSn() != null) {
			            		 fw.write(barcode.getSetno()+";"+barcode.getSn()+";"+barcode.getDisinfection()+";"+barcode.getSetnamech()+";"+dateString+";"+barcode.getBarcode()+";"+barcode.getDepno()+";;\r\n");
			            	}else {
			            		fw.write(barcode.getSetno()+";;"+barcode.getDisinfection()+";"+barcode.getSetnamech()+";"+dateString+";"+barcode.getBarcode()+";"+barcode.getDepno()+";;\r\n");
			            	}
				           
				            fw.flush();
		        		}
		            }

		            fw.close();
		        } catch (Exception e) {
		            System.out.println("Something Error:" +e );
		        }

		return ResultMsg.success("盤包製作成功").addData("");
	}

	@Override
	public ResultMsg listReprint(Integer pageNum, Integer pageSize, String depno, String barcode, String setno) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("depno", depno);
	    paramMap.put("barcode", barcode);
	    paramMap.put("setno", setno);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> barcodeList = csrBarcodeMapper.listReprint(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(barcodeList);    
		return  ResultMsg.success("Barcode列表").addData(pageInfo);
	}

	@Override
	public ResultMsg reprint(List<CsrBarcode> csrBarcodes, HttpServletRequest request) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
        try {
            // Constructs a FileWriter object given a file name.
            FileWriter fw = null;
            for(CsrBarcode barcode : csrBarcodes) {
            	System.out.println(barcode.getBarcode());
            	         	
	            	
	            	if(fw ==null) {
	            		fw = new FileWriter("D://Printer//TEST.txt");
	            	}

  		
	        		String dateString = dateFormat.format(barcode.getEffectivedate());
	        		
	        		barcode.setSetnamech(csrSetdata3m.getSetnoByName(barcode.getSetno()).getSetnamech());
	        		  		   		            	
	            	if(barcode.getSn() != null) {
	            		 fw.write(barcode.getSetno()+";"+barcode.getSn()+";"+barcode.getDisinfection()+";"+barcode.getSetnamech()+";"+dateString+";"+barcode.getBarcode()+";"+barcode.getDepno()+";;\r\n");
	            	}else {
	            		fw.write(barcode.getSetno()+";;"+barcode.getDisinfection()+";"+barcode.getSetnamech()+";"+dateString+";"+barcode.getBarcode()+";"+barcode.getDepno()+";;\r\n");
	            	}
		           
		            fw.flush();
        		}

            fw.close();
        } catch (Exception e) {
            System.out.println("Something Error:" +e );
        }

        return ResultMsg.success("盤包重印成功").addData("");
	}


	
}
