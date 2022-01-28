package com.htpe.mapper.nnew;

import com.htpe.bean.CsrRoom;

public interface CsrRoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrRoom record);

    int insertSelective(CsrRoom record);

    CsrRoom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrRoom record);

    int updateByPrimaryKey(CsrRoom record);
}