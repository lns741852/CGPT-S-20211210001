package com.htpe.mapper.nnew;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.htpe.bean.OneMenu;

@Mapper
public interface CsrMenuMapper {

	List<OneMenu> getMenuByUsername(String userno);

	List<OneMenu> listAuth();

}
