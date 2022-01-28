package com.htpe.mapper.old;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.htpe.bean.CsrAccount;


@Mapper
public interface AccountMapper {
	
	@Select("select depname from VGHTPETS.CSR_DepNO order by type limit #{0}")
	List<Map<String, Object>> getDepNo(int count);

	List<Map<String,Object>> getAccountList(Map<String, Object> paramMap);

	@Select("select count(distinct type) from VGHTPETS.CSR_DepNO")
	int getDepNoTypeCount();

	void addAccount(CsrAccount csrAccount);

	@Select("select count(id) from VGHTPETS.CSR_ACCOUNT where  USERNO= #{userno}")
	int getCountByUserNo(String userno);

	Map<String, Object> getAccountById(Integer id);

	void setAccount(CsrAccount csrAccount);

	@Delete("delete from VGHTPETS.CSR_ACCOUNT where id = #{id}")
	int deleteAccount(Integer id);

	@Select("select trim(empno) as empno from VGHTPETS.CSR_ACCOUNT where id= #{id}")
	String getEmpNoById(Integer id);

}