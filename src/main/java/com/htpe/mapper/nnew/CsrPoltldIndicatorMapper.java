package com.htpe.mapper.nnew;

import com.htpe.bean.CsrPoltldIndicator;

public interface CsrPoltldIndicatorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrPoltldIndicator record);

    int insertSelective(CsrPoltldIndicator record);

    CsrPoltldIndicator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrPoltldIndicator record);

    int updateByPrimaryKey(CsrPoltldIndicator record);
}