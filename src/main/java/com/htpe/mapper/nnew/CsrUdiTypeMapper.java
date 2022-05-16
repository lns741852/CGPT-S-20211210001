package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrFileResource;
import com.htpe.bean.CsrUdiType;

@Mapper
public interface CsrUdiTypeMapper {

	List<Map<String, Object>> listUDIType(Map<String, Object> paramMap);

	CsrUdiType getUDITypeById(Integer id);

	@Select("select count(*) from CSR_UDI_TYPE where  NAME= #{name}")
	int countCode(String name);

	int insertUDIType(CsrUdiType csrUdiType);

	int insertSecource(CsrFileResource resource);

	
	@Insert("insert into CSR_UDI_IMAGE(TYPE_ID,FILE_ID,IS_MAIN) values(#{sourceNum},#{num},#{main})")
	int insertUDITypeSource(Integer sourceNum, Integer num, int main);

	
	@Delete("delete from CSR_UDI_IMAGE where TYPE_ID=#{id}")
	void deleteUDIImage(Integer id);

	int updateUDIType(CsrUdiType csrUdi);
}