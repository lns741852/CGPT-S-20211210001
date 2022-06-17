package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrSetdataSeq;
import com.htpe.bean.CsrUdi;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrSetdataSeqMapper;
import com.htpe.mapper.nnew.CsrUdiMapper;
import com.htpe.service.SeqService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class SeqServiceImpl implements SeqService {
	
	@Autowired
	CsrSetdataSeqMapper csrSetdataSeqMapper;
	
	@Autowired
	CsrUdiMapper csrUdiMapper;

	@Override
	public ResultMsg saveSeq(Map<String, Object> addUdi, HttpServletRequest request) throws RequestPeriodException {
		CsrSetdataSeq seq = new CsrSetdataSeq();		
		Date date = new Date();		
		seq.setDatadate(date);
		seq.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		seq.setSetno((String)addUdi.get("setno"));
		seq.setSeq((String)addUdi.get("seq"));
		seq.setPublicType("Y");
		
		int count = csrSetdataSeqMapper.countSeqBySnAndSetno(seq);
		if(count >= 1) {	
			return ResultMsg.fail(500,"盤包序號重複使用").addData("");			
		}
		
		
		int num = csrSetdataSeqMapper.insertSeq(seq);
		if(num < 1) {
			throw new RequestPeriodException(401, "盤包建立失敗");
		}
		
		List<Map<String, Object>> udis = (List<Map<String, Object>>)addUdi.get("udi");
		CsrUdi udi = new CsrUdi();
		
		udis.forEach(e ->{
			udi.setId((Integer)e.get("id"));
			udi.setPackageId(seq.getId());
			udi.setStatus("1");
			csrUdiMapper.updateUDI(udi);
		});
		
		return ResultMsg.success("盤包建立成功").addData("");
	}

	@Override
	public ResultMsg getSeq() {
		return ResultMsg.success("已建立盤包查詢").addData(csrSetdataSeqMapper.listSeqByType());
	}



//	@Override
//	public ResultMsg listSeq(Integer pageNum, Integer pageSize, String searchName) {
//		Map<String, Object> paramMap = new HashMap<String, Object>();
//	    paramMap.put("pageNum", pageNum);
//	    paramMap.put("pageSize", pageSize);
//	    paramMap.put("searchName", searchName);
//	    PageHelper.startPage(pageNum, pageSize);
//		List<Map<String, Object>> seqList = csrSetdataSeqMapper.listSeq(paramMap);
//	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(seqList);    
//		return  ResultMsg.success("盤包序號列表").addData(pageInfo);
//	}
//
//	@Override
//	public ResultMsg listUnUDI() {
//		return  ResultMsg.success("未使用UDI").addData(csrSetdataSeqMapper.listUnUDI());
//	}
//
//	@Override
//	public ResultMsg saveSeq(CsrSetdataSeq seq, String ids,HttpServletRequest request) throws RequestPeriodException {
//
//		Date date = new Date();		
//		seq.setDatadate(date);
//		seq.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
//
//		int num = csrSetdataSeqMapper.insertSeq(seq);
//		if(num < 1) {
//			throw new RequestPeriodException(401, "盤包增改失敗");
//		}
//		if(!ids.isEmpty()) {
//			String[] idsList = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //去除重複值
//			for (String UDIid : idsList) {
//				int num2 =  csrSetdataSeqMapper.insertSeqUDI(seq.getId(),UDIid);
//				if(num2 < 1) {
//					throw new RequestPeriodException(401, "帳新增失敗");
//				}
//			}
//		}
//		return ResultMsg.success("帳號新增成功").addData("");
//	}
//
//	@Override
//	public ResultMsg getSeqById(Integer id) {
//		return ResultMsg.success("維護盤包查詢").addData(csrSetdataSeqMapper.getSeqById(id));
//	}
//
//	@Override
//	public ResultMsg listUDI(Integer id) {
//		return  ResultMsg.success("可使用UDI").addData(csrSetdataSeqMapper.listUDI(id));
//	}
//
//	@Override
//	public ResultMsg updateSeq(CsrSetdataSeq seq, String ids, HttpServletRequest request) throws RequestPeriodException {
//		Date date = new Date();		
//		seq.setDatadate(date);
//		seq.setDatauser((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
//
//		int num = csrSetdataSeqMapper.updateSeq(seq);
//		if(num < 1) {
//			throw new RequestPeriodException(401, "盤包修改失敗");
//		}
//		
//		int count = csrSetdataSeqMapper.countSeqUdiById(seq.getId());
//		
//		if(count>0) {
//			int num2 = csrSetdataSeqMapper.deleteSeqUdiBySeqId(seq.getId());
//			if(num2 < 1) {
//				throw new RequestPeriodException(500, "帳號修改失敗");
//			}		
//		}
//	
//		if(!ids.isEmpty()) {
//		String[] idsList = ids.replaceAll("(.,)\\1+","$1").split("\\,");  //去除重複值
//			for (String UDIid : idsList) {
//				int num3 =  csrSetdataSeqMapper.insertSeqUDI(seq.getId(),UDIid);
//				if(num3 < 1) {
//					throw new RequestPeriodException(401, "修改失敗");
//				}
//			}
//		}
//		return ResultMsg.success("修改成功").addData("");
//	}
//
//	@Override
//	public ResultMsg removeSeq(Integer id) throws RequestPeriodException {
//		int count = csrSetdataSeqMapper.deleteSeq(id);
//		if(count < 1) {
//			throw new RequestPeriodException(500, "盤包刪除失敗");
//		}
//		int count2 = csrSetdataSeqMapper.countSeqUdiById(id);
//		
//		if(count2>0) {
//			int num2 = csrSetdataSeqMapper.deleteSeqUdiBySeqId(id);
//			if(num2 < 1) {
//				throw new RequestPeriodException(500, "盤包刪除失敗");
//			}
//		}
//		return ResultMsg.success("盤包刪除成功").addData("");
//	}
//	

}
