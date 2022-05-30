package com.htpe.mapper.nnew;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrCostcenter;
import com.htpe.bean.CsrRequesition;

public interface CsrRequesitionMapper {
    int deleteByPrimaryKey(Integer reqId);

    CsrRequesition selectByPrimaryKey(Integer reqId);

    int updateByPrimaryKeySelective(CsrRequesition record);

    int updateByPrimaryKey(CsrRequesition record);

	int insertApply(CsrRequesition csrRequesition);

	CsrRequesition getReqPrint(Integer reqId);
	
	
	@Select("select * from CSR_COSTCENTER where  CENTERNO=#{centerno}")
	CsrCostcenter getCenterByno(String centerno);

	List<CsrRequesition> getReqByDepno(String depno);
	
	int updateReq(CsrRequesition csrRequesition);
}