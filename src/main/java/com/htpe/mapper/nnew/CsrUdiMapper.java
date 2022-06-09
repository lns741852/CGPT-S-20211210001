package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrUdi;

@Mapper
public interface CsrUdiMapper {
	
	List<CsrUdi> listUDI(Integer typeId);
	
    @Select("select count(*) from CSR_UDI where  UDI= #{udi}")
	int countCode(String udi);
    
	int insertUDI(CsrUdi csrUdi);
     
    CsrUdi getUDIById(Integer id);
    
	int updateUDI(CsrUdi csrUdi);
    
    @Select("select trim(UDI) as UDI from CSR_UDI where id = #{id}")
	String getCodeById(Integer id);
    
	@Delete("delete from CSR_UDI where id = #{id}")
	int deleteUDI(Integer id);

//	int deleteFile(CsrUdi csrUdi);


	
    


	
}