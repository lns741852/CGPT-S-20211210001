package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrBox;

public interface CsrBoxMapper {

	List<Map<String, Object>> listBox(Map<String, Object> paramMap);

	@Select("select count(*) from CSR_UDI_BOX where  UDI= #{udi}")
	int countBox(String udi);

	int insertBox(CsrBox csrBox);

	CsrBox getBoxById(Integer id);

	@Select("select trim(UDI) as UDI from CSR_UDI_BOX where id= #{id}")
	String getBoxColumnById(Integer id);

	@Delete("delete from CSR_UDI_BOX where ID = #{id}")
	int deleteBox(Integer id);

	int updateBox(CsrBox csrBox);
	
	@Select("SELECT id from CSR_UDI_BOX where UDI=#{name} AND STATUS=1")
	CsrBox getBoxByNameAndStatus(String name);
	
	@Select("SELECT ID,UDI,STATUS from CSR_UDI_BOX where UDI=#{name}")
	CsrBox getBoxByName(String name);

}
