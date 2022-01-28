package com.htpe.mapper.nnew;

import com.htpe.bean.CsrBarcode;

public interface CsrBarcodeMapper {
    int deleteByPrimaryKey(Integer barcodeid);

    int insert(CsrBarcode record);

    int insertSelective(CsrBarcode record);

    CsrBarcode selectByPrimaryKey(Integer barcodeid);

    int updateByPrimaryKeySelective(CsrBarcode record);

    int updateByPrimaryKey(CsrBarcode record);
}