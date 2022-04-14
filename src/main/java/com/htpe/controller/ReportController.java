package com.htpe.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrSetdata3m;
import com.htpe.service.ReportService;
import com.htpe.utils.ResultMsg;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;

@RestController
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	/**
	 *每月過期包一覽表(CSR)
	 */
	@GetMapping("/report/02")
	public ResultMsg  listReport02(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="start", required = false) String start,
			@RequestParam(value="end", required = false) String end) {
		
	    return   reportService.listReport02(pageNum,pageSize,start,end);
	}
	
	/**
	 *每月過期包一覽表(CSR)_匯出
	 */
	@GetMapping(value="/report/02/export",produces = "application/octet-stream")
	public void  exportReport02(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="start", required = false) String start,
			@RequestParam(value="end", required = false) String end,
			HttpServletResponse response) {
		
		 List<CsrSetdata3m> exportReport02 = reportService.exportReport02(pageNum,pageSize,start,end);
		
		 ExportParams params = new ExportParams("每月過期包一覽表(CSR)","工作頁",ExcelType.XSSF);
		 Workbook workbook = ExcelExportUtil.exportExcel(params, CsrSetdata3m.class, exportReport02);
	        ServletOutputStream outputStream = null;
	        try {
	            //流形式
	            response.setHeader("content-type","application/octet-stream");
	            //中文乱码
	            response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode("測試.xlsx","UTF-8"));
	            outputStream = response.getOutputStream();
	            workbook.write(outputStream);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }finally {
	            if (null != outputStream){
	                try {
	                    outputStream.flush();
	                    outputStream.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
	}
	
	
	

}
