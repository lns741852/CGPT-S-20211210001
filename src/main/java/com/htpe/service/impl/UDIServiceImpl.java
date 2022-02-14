package com.htpe.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrUdi;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrUdiMapper;
import com.htpe.service.UDIService;
import com.htpe.utils.ResultMsg;

@Service
public class UDIServiceImpl  implements UDIService{

	@Autowired
	CsrUdiMapper csrUdiMapper;

	@Override
	public ResultMsg listUDI( Integer pageNum, Integer pageSize,String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> listUDI = csrUdiMapper.listUDI(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(listUDI);    
		return  ResultMsg.success("單一盤包列表").addData(pageInfo);
	}

	@Override
	public ResultMsg saveUDI(CsrUdi csrUdi, HttpServletRequest request, 
			MultipartFile pic1, MultipartFile pic2,MultipartFile pic3,
			MultipartFile file1, MultipartFile file2, MultipartFile file3) throws RequestPeriodException {
		
		int count = csrUdiMapper.countCode(csrUdi.getCode());
		if(count >= 1) {	
			return ResultMsg.fail(403,"器械已使用").addData("");			
		}
		
		Map<String,MultipartFile> mapFiles = new HashMap<String,MultipartFile>();
		if(pic1 != null) {mapFiles.put("pic1",pic1);}
		if(pic2 != null) {mapFiles.put("pic2",pic2);}
		if(pic3 != null) {mapFiles.put("pic3",pic3);}
		if(file1 != null) {mapFiles.put("file1",file1);}
		if(file2 != null) {mapFiles.put("file2",file2);}
		if(file3 != null) {mapFiles.put("file3",file3);}
	
		Date date = new Date();		
		csrUdi.setDatadate(date);
		csrUdi.setDelType("N");
		csrUdi.setUpdType("Y");
		csrUdi.setDatauser(request.getUserPrincipal().getName());		

		for(Map.Entry<String, MultipartFile> file : mapFiles.entrySet()) {
			try {
				String filename = file.getValue().getOriginalFilename();
				String iconpath = date.getTime()+"_"+filename;		//檔名
		
				String filePath = System.getProperty("user.dir");
				String realPath = filePath+"\\src\\main\\resources\\static\\file\\";
				String path =realPath +iconpath;
				file.getValue().transferTo(new File(path));
							
				switch (file.getKey()) {
					case "pic1":
						csrUdi.setPicpath1(iconpath);
						break;
					case "pic2":
						csrUdi.setPicpath2(iconpath);
						break;
					case "pic3":
						csrUdi.setPicpath3(iconpath);
						break;
					case "file1":
						csrUdi.setFilepath1(iconpath);
						break;
					case "file2":
						csrUdi.setFilepath2(iconpath);
						break;
					case "file3":
						csrUdi.setFilepath3(iconpath);
						break;

				}					
			}catch (Exception e) {
				throw new RequestPeriodException(500, "檔案添加失敗");
			}			
		}	
		int num = csrUdiMapper.insertUDI(csrUdi);
		if(num < 1) {
			throw new RequestPeriodException(500, "器械添加失敗");
		}
		return ResultMsg.success("器械新增成功").addData("");
	}

	@Override
	public ResultMsg getUDIById(Integer id) {		
		return ResultMsg.success("器械查詢").addData(csrUdiMapper.getUDIById(id));
	}

	@Override
	public ResultMsg updateUDIById(CsrUdi csrUdi, Integer id, HttpServletRequest request, MultipartFile pic1,
			MultipartFile pic2, MultipartFile pic3, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws RequestPeriodException {
		int count = csrUdiMapper.countCode(csrUdi.getCode());
		String changeCode = csrUdi.getCode();
		String code = csrUdiMapper.getCodeById(id);
		if((!changeCode.equals(code)) && count >=1) {					
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		
		Map<String,MultipartFile> mapFiles = new HashMap<String,MultipartFile>();
		if(pic1 != null) {mapFiles.put("pic1",pic1);}
		if(pic2 != null) {mapFiles.put("pic2",pic2);}
		if(pic3 != null) {mapFiles.put("pic3",pic3);}
		if(file1 != null) {mapFiles.put("file1",file1);}
		if(file2 != null) {mapFiles.put("file2",file2);}
		if(file3 != null) {mapFiles.put("file3",file3);}
		
		Date date = new Date();
		csrUdi.setId(id);
		csrUdi.setDatadate(date);
		csrUdi.setDelType("N");
		csrUdi.setUpdType("Y");
		csrUdi.setDatauser(request.getUserPrincipal().getName());	

		for(Map.Entry<String, MultipartFile> file : mapFiles.entrySet()) {
			try {
				String filename = file.getValue().getOriginalFilename();
				String iconpath = date.getTime()+"_"+filename;		//檔名
		
				String filePath = System.getProperty("user.dir");
				String realPath = filePath+"\\src\\main\\resources\\static\\file\\";
				String path =realPath +iconpath;
				file.getValue().transferTo(new File(path));
				
				switch (file.getKey()) {
					case "pic1":
						csrUdi.setPicpath1(iconpath);
						break;
					case "pic2":
						csrUdi.setPicpath2(iconpath);
						break;
					case "pic3":
						csrUdi.setPicpath3(iconpath);
						break;
					case "file1":
						csrUdi.setFilepath1(iconpath);
						break;
					case "file2":
						csrUdi.setFilepath2(iconpath);
						break;
					case "file3":
						csrUdi.setFilepath3(iconpath);
						break;

				}					
			}catch (Exception e) {
				throw new RequestPeriodException(500, "檔案添加失敗");
			}			
		}	
		int num = csrUdiMapper.updateUDI(csrUdi);
		if(num< 1) {
			throw new RequestPeriodException(500, "器械修改失敗");
		}
		return ResultMsg.success("器械修改成功").addData("");
	}

	@Override
	public ResultMsg removeFile(Integer id, String filename) throws RequestPeriodException {
		CsrUdi udi = csrUdiMapper.getUDIById(id);
		udi.setId(id);
		
		if(!udi.getPicpath1().equals(filename)) {udi.setPicpath1(null);}
		if(!udi.getPicpath2().equals(filename)) {udi.setPicpath2(null);}
		if(!udi.getPicpath3().equals(filename)) {udi.setPicpath3(null);}
		if(!udi.getFilepath1().equals(filename)) {udi.setFilepath1(null);}
		if(!udi.getFilepath2().equals(filename)) {udi.setFilepath2(null);}
		if(!udi.getFilepath3().equals(filename)) {udi.setFilepath3(null);}
					
		int num = csrUdiMapper.deleteFile(udi);
		if(num < 1) {
			throw new RequestPeriodException(500, "檔案刪除失敗");
		}		
			
		String filePath = System.getProperty("user.dir");
		String realPath = filePath+"\\src\\main\\resources\\static\\file\\";
		String path =realPath +filename;
		File file = new File(path);
		if(!file.delete()) {
			throw new RequestPeriodException(500, "檔案刪除失敗");
		}
		
		return ResultMsg.success("檔案刪除成功成功").addData("");
	}

	@Override
	public ResultMsg removeUDI(Integer id) throws RequestPeriodException {
		int count = csrUdiMapper.deleteUDI(id);
		if(count < 1) {
			throw new RequestPeriodException(500, "器械刪除失敗");
		}
		return ResultMsg.success("器械刪除成功").addData("");
	}

}

