package com.htpe.mapper.nnew;

import com.htpe.bean.CsrCostcenter;

public interface CsrCostcenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrCostcenter record);

    int insertSelective(CsrCostcenter record);

    CsrCostcenter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrCostcenter record);

    int updateByPrimaryKey(CsrCostcenter record);
}