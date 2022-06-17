package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.bind.annotation.XmlInlineBinaryData;

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

	@Insert("insert into CSR_SETDATA_UDI(SETNO_ID,UDITYPE_ID,NUM) values(#{id},#{udiId},#{num})")
	int insertSetnoUDI(String id, Object udiId, Object num);
	
	
	@Delete("delete from CSR_SETDATA_UDI where SETNO_ID=#{id}")
	void deleteUDIBySetNO(String id);

	@Select("select trim(A.SETNO_ID) as setnoId,trim(A.UDITYPE_ID) as uditypeId,num,D.NAME as name  "
			+ "from CSR_SETDATA_UDI as A "
			+ "left join CSR_UDI_TYPE as D "
			+ "ON A.UDITYPE_ID= D.id "
			+ "where  A.SETNO_ID=#{id} ")
	List<Map<String, Object>>  getSetnoUDI(Integer id);
}