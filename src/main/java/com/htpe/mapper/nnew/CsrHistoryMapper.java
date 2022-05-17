package com.htpe.mapper.nnew;

import org.apache.ibatis.annotations.Update;

import com.htpe.bean.CsrHistory;

public interface CsrHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrHistory record);

    int insertSelective(CsrHistory record);

    CsrHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrHistory record);

    int updateByPrimaryKey(CsrHistory record);

	int insertHistory(CsrHistory history);

	
	@Update("update CSR_HISTORY set islast='N' where barcode=#{barcode}")
	int updateHistory(CsrHistory history);
}