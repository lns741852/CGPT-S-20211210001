package com.htpe.service.impl;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrFileResource;
import com.htpe.bean.CsrUdiType;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrUdiMapper;
import com.htpe.mapper.nnew.CsrUdiTypeMapper;
import com.htpe.service.UDITypeService;
import com.htpe.utils.ResultMsg;

@Service
public class UDITypeServiceImpl  implements UDITypeService{
	
	@Autowired
	CsrUdiTypeMapper csrUdiTypeMapper;

	@Override
	public ResultMsg listUDIType(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> listUDIType = csrUdiTypeMapper.listUDIType(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(listUDIType);    
		return  ResultMsg.success("器械類型類列表").addData(pageInfo);
	}

	@Override
	public ResultMsg getUDITypeById(Integer id) {
		return ResultMsg.success("器械類型類查詢").addData(csrUdiTypeMapper.getUDITypeById(id));
	}

	@Override
	public ResultMsg saveUDIType(CsrUdiType csrUdiType, HttpServletRequest request, MultipartFile[] file) {
		int count = csrUdiTypeMapper.countCode(csrUdiType.getName());
		if(count >= 1) {	
			return ResultMsg.fail(403,"器械類型已使用").addData("");			
		}
		
		int num = csrUdiTypeMapper.insertUDIType(csrUdiType);
		if(num < 1) {
			throw new RequestPeriodException(500, "器械類型類型添加失敗");
		}
				
		int i=0;
		
		StringBuffer buffer = new StringBuffer();
		for(MultipartFile multipartFile : file) {
			String str = fileUpload(multipartFile,request,csrUdiType.getId(),i);
			i+=1;
		}

		return ResultMsg.success("器械類型新增成功").addData("");
	
	}
	
	
	public String fileUpload(MultipartFile file,HttpServletRequest request,Integer num,int i) {		
		try {
			Date date = new Date();		
			String filename = file.getOriginalFilename();				//檔名
			String iconpath = date.getTime()+"_"+filename;		
			
			String filePath = System.getProperty("user.dir");						//項目路徑
			String realPath = filePath+"\\src\\main\\resources\\static\\file\\";	//上傳路徑
			String path =realPath +iconpath;
			
			file.transferTo(new File(path));					//上傳
			
			
			CsrFileResource resource = new CsrFileResource();
			
			resource.setResourceName(iconpath);
//			resource.setLocalPath(path);
			resource.setDatadate(date);
			resource.setDatauserno(request.getUserPrincipal().getName());
			
			
			
			Integer sourceNum = csrUdiTypeMapper.insertSecource(resource);
			if(sourceNum < 1) {
				throw new RequestPeriodException(500, "器械類型類型添加失敗");
			}
			
			if(i == 0) {
				csrUdiTypeMapper.insertUDITypeSource(num,resource.getId(),1);
			}else {
				csrUdiTypeMapper.insertUDITypeSource(num,resource.getId(),0);
			}
							
			return iconpath;
		}catch (Exception e) {
			throw new RequestPeriodException(500, "圖片添加失敗");
		}
	}

	@Override
	public ResultMsg updateUDITypeById(CsrUdiType csrUdi, HttpServletRequest request, MultipartFile[] file) {
		
		int count = csrUdiTypeMapper.countCode(csrUdi.getName());
		Integer changeId = csrUdi.getId();
		CsrUdiType type = csrUdiTypeMapper.getUDITypeById(csrUdi.getId());
		if((!changeId.equals(type.getId())) && count >=1) {					
			return ResultMsg.fail(500,"器械類型已使用").addData("");			
		}
		
		int num = csrUdiTypeMapper.updateUDIType(csrUdi);
		if(num < 1) {
			throw new RequestPeriodException(500, "器械類型類型添加失敗");
		}
		
		csrUdiTypeMapper.deleteUDIImage(csrUdi.getId());
		
		int i=0;
		
		StringBuffer buffer = new StringBuffer();
		for(MultipartFile multipartFile : file) {
			String str = fileUpload(multipartFile,request,csrUdi.getId(),i);
			i+=1;
		}

		return ResultMsg.success("器械類型新增成功").addData("");
	
	}
	
	
	
	

}
