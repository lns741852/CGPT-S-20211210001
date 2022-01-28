package com.htpe.mapper.nnew;

import com.htpe.bean.CsrSetdataPosition;

public interface CsrSetdataPositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrSetdataPosition record);

    int insertSelective(CsrSetdataPosition record);

    CsrSetdataPosition selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrSetdataPosition record);

    int updateByPrimaryKey(CsrSetdataPosition record);
}