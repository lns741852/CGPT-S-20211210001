package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrUdi;

@Mapper
public interface CsrUdiMapper {
	
	List<Map<String, Object>> listUDI(Map<String, Object> paramMap);
	
    @Select("select count(*) from CSR_UDI where  CODE= #{code}")
	int countCode(String code);
    
    CsrUdi getUDIById(Integer id);
    
	int insertUDI(CsrUdi csrUdi);

    CsrUdi selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrUdi record);

    int updateByPrimaryKey(CsrUdi record);
    
    @Select("select trim(CODE) as CODE from CSR_UDI where id = #{id}")
	String getCodeById(Integer id);

	int updateUDI(CsrUdi csrUdi);

	int deleteFile(CsrUdi csrUdi);

	@Delete("delete from CSR_UDI where id = #{id}")
	int deleteUDI(Integer id);

	
    


	
}