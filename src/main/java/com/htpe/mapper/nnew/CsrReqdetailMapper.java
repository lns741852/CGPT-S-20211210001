package com.htpe.mapper.nnew;

import com.htpe.bean.CsrReqdetail;

public interface CsrReqdetailMapper {
    int deleteByPrimaryKey(Integer rdId);

    CsrReqdetail selectByPrimaryKey(Integer rdId);

    int updateByPrimaryKeySelective(CsrReqdetail record);

    int updateByPrimaryKey(CsrReqdetail record);

	int insertReqdetail(CsrReqdetail req);
}