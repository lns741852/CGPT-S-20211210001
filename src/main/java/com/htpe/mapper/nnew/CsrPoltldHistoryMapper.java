package com.htpe.mapper.nnew;

import com.htpe.bean.CsrPoltldHistory;

public interface CsrPoltldHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrPoltldHistory record);

    int insertSelective(CsrPoltldHistory record);

    CsrPoltldHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrPoltldHistory record);

    int updateByPrimaryKey(CsrPoltldHistory record);
}