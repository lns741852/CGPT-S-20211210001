package com.htpe.mapper.nnew;

import com.htpe.bean.CsrPoltld;

public interface CsrPoltldMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrPoltld record);

    int insertSelective(CsrPoltld record);

    CsrPoltld selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrPoltld record);

    int updateByPrimaryKey(CsrPoltld record);
}