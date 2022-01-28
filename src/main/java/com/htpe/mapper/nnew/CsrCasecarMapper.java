package com.htpe.mapper.nnew;

import com.htpe.bean.CsrCasecar;

public interface CsrCasecarMapper {
    int deleteByPrimaryKey(Integer casecarId);

    int insert(CsrCasecar record);

    int insertSelective(CsrCasecar record);

    CsrCasecar selectByPrimaryKey(Integer casecarId);

    int updateByPrimaryKeySelective(CsrCasecar record);

    int updateByPrimaryKey(CsrCasecar record);
}