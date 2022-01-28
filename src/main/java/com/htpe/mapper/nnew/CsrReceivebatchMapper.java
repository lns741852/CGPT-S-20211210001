package com.htpe.mapper.nnew;

import com.htpe.bean.CsrReceivebatch;

public interface CsrReceivebatchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrReceivebatch record);

    int insertSelective(CsrReceivebatch record);

    CsrReceivebatch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrReceivebatch record);

    int updateByPrimaryKey(CsrReceivebatch record);
}