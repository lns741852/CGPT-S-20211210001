package com.htpe.mapper.nnew;

import com.htpe.bean.CsrHistory;

public interface CsrHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrHistory record);

    int insertSelective(CsrHistory record);

    CsrHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrHistory record);

    int updateByPrimaryKey(CsrHistory record);

	int insertHistory(CsrHistory history);
}