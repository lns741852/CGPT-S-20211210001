package com.htpe.mapper.nnew;

import com.htpe.bean.CsrSetdataSeq;

public interface CsrSetdataSeqMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrSetdataSeq record);

    int insertSelective(CsrSetdataSeq record);

    CsrSetdataSeq selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrSetdataSeq record);

    int updateByPrimaryKey(CsrSetdataSeq record);
}