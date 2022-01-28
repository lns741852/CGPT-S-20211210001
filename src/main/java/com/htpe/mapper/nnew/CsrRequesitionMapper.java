package com.htpe.mapper.nnew;

import com.htpe.bean.CsrRequesition;

public interface CsrRequesitionMapper {
    int deleteByPrimaryKey(Integer reqId);

    int insert(CsrRequesition record);

    int insertSelective(CsrRequesition record);

    CsrRequesition selectByPrimaryKey(Integer reqId);

    int updateByPrimaryKeySelective(CsrRequesition record);

    int updateByPrimaryKey(CsrRequesition record);
}