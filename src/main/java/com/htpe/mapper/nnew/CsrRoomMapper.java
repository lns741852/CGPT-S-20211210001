package com.htpe.mapper.nnew;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.htpe.bean.CsrRoom;


public interface CsrRoomMapper {

	List<Map<String, Object>> listRoom(Map<String, Object> paramMap);

	int insertRoom(@Valid CsrRoom csrRoom);

	CsrRoom getRoomById(Integer id);

	int updateRoom(@Valid CsrRoom csrRoom);

	@Delete("delete from CSR_Room where ID = #{id}")
	int deleteRoom(Integer id);

	@Select("select count(*) from CSR_Room where ROOMNO=#{roomno}")
	int countRoomByNo(String roomno);

	@Select("select trim(ROOMNO) ROOMNO from CSR_Room where ID=#{id}")
	String getUserNoById(Integer id);
}