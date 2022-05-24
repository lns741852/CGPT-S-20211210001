package com.htpe.mapper.nnew;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrCostcenter;

public interface CsrCostcenterMapper {


	@Select("select ID,CENTERNAME,CENTERNO from CSR_COSTCENTER")
	List<CsrCostcenter> listCostcenter();
}