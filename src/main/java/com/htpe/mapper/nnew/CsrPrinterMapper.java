package com.htpe.mapper.nnew;

import com.htpe.bean.CsrPrinter;

public interface CsrPrinterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrPrinter record);

    int insertSelective(CsrPrinter record);

    CsrPrinter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrPrinter record);

    int updateByPrimaryKey(CsrPrinter record);
}