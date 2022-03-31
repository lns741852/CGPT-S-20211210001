package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrSetdataPosition;

@Mapper
public interface CsrSetdataPositionMapper {

	List<Map<String, Object>> listPosition(Map<String, Object> paramMap);

	@Select("select count(*) from CSR_SETDATA_POSITION where  CODE= #{code}")
	int countPosition(String code);

	int insertPosition(@Valid CsrSetdataPosition csrSetdataPosition);

	CsrSetdataPosition getPositionById(Integer id);

	@Select("select trim(CODE) as CODE from CSR_SETDATA_POSITION where id= #{id}")
	String getPositionColumnById(Integer id);

	int updatePosition(@Valid CsrSetdataPosition csrSetdataPosition);

	@Delete("delete from CSR_SETDATA_POSITION where ID = #{id}")
	int deletePosition(Integer id);
}