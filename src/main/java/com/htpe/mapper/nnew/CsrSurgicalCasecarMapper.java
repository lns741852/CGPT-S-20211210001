package com.htpe.mapper.nnew;

import com.htpe.bean.CsrSurgicalCasecar;

public interface CsrSurgicalCasecarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrSurgicalCasecar record);

    int insertSelective(CsrSurgicalCasecar record);

    CsrSurgicalCasecar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrSurgicalCasecar record);

    int updateByPrimaryKey(CsrSurgicalCasecar record);
}