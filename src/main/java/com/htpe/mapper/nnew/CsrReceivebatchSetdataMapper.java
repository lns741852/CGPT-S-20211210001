package com.htpe.mapper.nnew;

import com.htpe.bean.CsrReceivebatchSetdata;

public interface CsrReceivebatchSetdataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrReceivebatchSetdata record);

    int insertSelective(CsrReceivebatchSetdata record);

    CsrReceivebatchSetdata selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrReceivebatchSetdata record);

    int updateByPrimaryKey(CsrReceivebatchSetdata record);
}