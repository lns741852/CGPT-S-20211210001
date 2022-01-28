package com.htpe.mapper.nnew;

import com.htpe.bean.CsrSetdataSeqUdi;

public interface CsrSetdataSeqUdiMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrSetdataSeqUdi record);

    int insertSelective(CsrSetdataSeqUdi record);

    CsrSetdataSeqUdi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrSetdataSeqUdi record);

    int updateByPrimaryKey(CsrSetdataSeqUdi record);
}