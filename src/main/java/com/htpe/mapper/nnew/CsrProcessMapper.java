package com.htpe.mapper.nnew;

import com.htpe.bean.CsrProcess;

public interface CsrProcessMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrProcess record);

    int insertSelective(CsrProcess record);

    CsrProcess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrProcess record);

    int updateByPrimaryKey(CsrProcess record);
}