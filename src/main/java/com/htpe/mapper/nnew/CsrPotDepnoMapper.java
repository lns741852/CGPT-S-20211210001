package com.htpe.mapper.nnew;

import com.htpe.bean.CsrPotDepno;

public interface CsrPotDepnoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrPotDepno record);

    int insertSelective(CsrPotDepno record);

    CsrPotDepno selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrPotDepno record);

    int updateByPrimaryKey(CsrPotDepno record);
}