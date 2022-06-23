package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrCostcenter;
import com.htpe.bean.CsrRequesition;
import com.htpe.bean.Search;
import com.htpe.utils.ResultMsg;

public interface CsrRequesitionMapper {

	int insertApply(CsrRequesition csrRequesition);

	CsrRequesition getReqPrint(Integer reqId);
	
	
	@Select("select * from CSR_COSTCENTER where  CENTERNO=#{centerno}")
	CsrCostcenter getCenterByno(String centerno);

	List<CsrRequesition> getReqByDepno(String depno);
	
	int updateReq(CsrRequesition csrRequesition);

	CsrRequesition getReqByIdforChecking(Integer id);
	
	
	@Select("select * from CSR_REQUESITION where REQ_ID=#{id}")
	CsrRequesition getReqById(Integer id);

	CsrRequesition getreqByNoAndPatientAndDepno(CsrRequesition csrRequesition);

	List<CsrRequesition> listReqForSearch5(Search search);

	List<CsrRequesition>  getReqByDepnoAndRoom(String roomno, String depnoask);

}