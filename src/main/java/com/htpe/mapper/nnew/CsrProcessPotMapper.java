package com.htpe.mapper.nnew;

import com.htpe.bean.CsrProcessPot;

public interface CsrProcessPotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrProcessPot record);

    int insertSelective(CsrProcessPot record);

    CsrProcessPot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrProcessPot record);

    int updateByPrimaryKey(CsrProcessPot record);
}