package com.htpe.mapper.nnew;

import java.util.List;

import com.htpe.bean.CsrReqdetail;

public interface CsrReqdetailMapper {

	int insertReqdetail(CsrReqdetail req);

	int updateReqDetail(CsrReqdetail req);

	CsrReqdetail getRdById(Integer rdId);

	List<CsrReqdetail> getRdByReqId(Integer id);
}