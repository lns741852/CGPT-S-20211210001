package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrAccount;

@Mapper
public interface CsrAccountMapper {
   
    CsrAccount getAccountByUserno(String username);

	List<Map<String, Object>> listAccount(Map<String, Object> paramMap);

	@Select("select count(distinct type) from CSR_DEPNO")
	int countDepno();
	
	@Select("select  trim(DEPNO) as DEPNO, trim(DEPNAME) as DEPNAME from CSR_DepNO order by type limit #{0}")
	List<Map<String, Object>> listDepno(int count);
	
	@Select("select count(*) from CSR_ACCOUNT where  USERNO= #{userno}")
	int countUserno(String userno);

	int insertAccount(CsrAccount csrAccount);
	
	CsrAccount getAccountById(Integer id);
	
	@Select("select trim(empno) as empno from CSR_ACCOUNT where id = #{id}")
	String getEmpNoById(Integer id);
	
	int updateAccount(CsrAccount csrAccount);
	
	@Delete("delete from CSR_ACCOUNT where id = #{id}")
	int deleteAccount(Integer id);
		
	
}