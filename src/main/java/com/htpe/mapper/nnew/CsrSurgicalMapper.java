package com.htpe.mapper.nnew;

import com.htpe.bean.CsrSurgical;

public interface CsrSurgicalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CsrSurgical record);

    int insertSelective(CsrSurgical record);

    CsrSurgical selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CsrSurgical record);

    int updateByPrimaryKey(CsrSurgical record);
}