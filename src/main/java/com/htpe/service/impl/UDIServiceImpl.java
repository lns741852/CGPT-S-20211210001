package com.htpe.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrUdi;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrUdiMapper;
import com.htpe.service.UDIService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class UDIServiceImpl  implements UDIService{

	@Autowired
	CsrUdiMapper csrUdiMapper;

	@Override
	public ResultMsg listUDI( Integer typeId) {
		List<CsrUdi> listUDI = csrUdiMapper.listUDI(typeId);
		return  ResultMsg.success("單一盤包列表").addData(listUDI);
	}

	@Override
	public ResultMsg saveUDI(CsrUdi csrUdi, HttpServletRequest request) {
			
		int count = csrUdiMapper.countCode(csrUdi.getUdi());
		if(count >= 1) {	
			return ResultMsg.fail(403,"器械已使用").addData("");			
		}
		
		Date date = new Date();		
		
		csrUdi.setCreateTime(date);
		csrUdi.setCreateId((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
	
		csrUdiMapper.insertUDI(csrUdi);

		return ResultMsg.success("器械新增成功").addData("");
	}

	@Override
	public ResultMsg getUDIById(Integer id) {		
		return ResultMsg.success("器械查詢").addData(csrUdiMapper.getUDIById(id));
	}

	@Override
	public ResultMsg updateUDIById(CsrUdi csrUdi, Integer id, HttpServletRequest request) {
		int count = csrUdiMapper.countCode(csrUdi.getUdi());
		String changeCode = csrUdi.getUdi();
		String code = csrUdiMapper.getCodeById(id);
		if((!changeCode.equals(code)) && count >=1) {					
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
				
		Date date = new Date();
		csrUdi.setId(id);
		csrUdi.setUpdateTime(date);
		csrUdi.setUpdateId((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		
		CsrUdi udi = csrUdiMapper.getUDIById(id);


		int num = csrUdiMapper.updateUDI(csrUdi);
		if(num< 1) {
			throw new RequestPeriodException(500, "器械修改失敗");
		}
		return ResultMsg.success("器械修改成功").addData("");
	}

//	@Override
//	public ResultMsg removeFile(Integer id, String filename) throws RequestPeriodException {
//		CsrUdi udi = csrUdiMapper.getUDIById(id);
//		udi.setId(id);
//		
//		if(udi.getPicpath1().equals(filename)) {udi.setPicpath1("");}
//		if(udi.getPicpath2().equals(filename)) {udi.setPicpath2("");}
//		if(udi.getPicpath3().equals(filename)) {udi.setPicpath3("");}
//		if(udi.getFilepath1().equals(filename)) {udi.setFilepath1("");}
//		if(udi.getFilepath2().equals(filename)) {udi.setFilepath2("");}
//		if(udi.getFilepath3().equals(filename)) {udi.setFilepath3("");}
//					
//		int num = csrUdiMapper.deleteFile(udi);
//		if(num < 1) {
//			throw new RequestPeriodException(500, "檔案刪除失敗");
//		}		
//			
//		String filePath = System.getProperty("user.dir");
//		String realPath = filePath+"\\src\\main\\resources\\static\\file\\";
//		String path =realPath +filename;
//		File file = new File(path);
//		if(!file.delete()) {
//			throw new RequestPeriodException(500, "檔案刪除失敗");
//		}
//		
//		return ResultMsg.success("檔案刪除成功成功").addData("");
//	}

	@Override
	public ResultMsg removeUDI(Integer id) throws RequestPeriodException {
		int count = csrUdiMapper.deleteUDI(id);
		if(count < 1) {
			throw new RequestPeriodException(500, "器械刪除失敗");
		}
		return ResultMsg.success("器械刪除成功").addData("");
	}

	@Override
	public ResultMsg getUDIByName(String udi) {
		return ResultMsg.success("器械查詢").addData(csrUdiMapper.getUDIByName(udi));
	}

}

