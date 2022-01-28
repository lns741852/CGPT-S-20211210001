package com.htpe.mapper.nnew;

import com.htpe.bean.CsrBarcodeInOut;

public interface CsrBarcodeInOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrBarcodeInOut record);

    int insertSelective(CsrBarcodeInOut record);

    CsrBarcodeInOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrBarcodeInOut record);

    int updateByPrimaryKey(CsrBarcodeInOut record);
}