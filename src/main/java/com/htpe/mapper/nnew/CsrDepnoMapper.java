package com.htpe.mapper.nnew;

import com.htpe.bean.CsrDepno;

public interface CsrDepnoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrDepno record);

    int insertSelective(CsrDepno record);

    CsrDepno selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrDepno record);

    int updateByPrimaryKey(CsrDepno record);
}