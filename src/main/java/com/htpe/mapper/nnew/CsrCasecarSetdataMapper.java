package com.htpe.mapper.nnew;

import com.htpe.bean.CsrCasecarSetdata;

public interface CsrCasecarSetdataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrCasecarSetdata record);

    int insertSelective(CsrCasecarSetdata record);

    CsrCasecarSetdata selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrCasecarSetdata record);

    int updateByPrimaryKey(CsrCasecarSetdata record);
}