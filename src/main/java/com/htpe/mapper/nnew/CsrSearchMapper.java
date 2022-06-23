package com.htpe.mapper.nnew;

import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface CsrSearchMapper {

	@Select("Select count(*) AS store from CSR_BARCODE where status='3' AND setno=#{setno} AND depno=#{depno}")
	int getStoreCountBySetNoAndDepno(Map<String, Object> paramMap);

	@Select("Select count(*) AS store from CSR_BARCODE where status='1' AND setno=#{setno} AND depno=#{depno}")
	int getPotCountBySetNoAndDepno(Map<String, Object> paramMap);

	@Select("Select count(*) AS store from CSR_BARCODE where status='2' AND setno=#{setno} AND depno=#{depno}")
	int getWarehousingCountBySetNoAndDepno(Map<String, Object> paramMap);

}
