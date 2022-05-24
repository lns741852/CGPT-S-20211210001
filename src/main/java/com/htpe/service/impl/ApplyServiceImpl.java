package com.htpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.bean.CsrCostcenter;
import com.htpe.mapper.nnew.CsrCostcenterMapper;
import com.htpe.mapper.nnew.CsrRequesitionMapper;
import com.htpe.service.ApplyService;
import com.htpe.utils.ResultMsg;

@Service
public class ApplyServiceImpl implements ApplyService {
	
	@Autowired
	CsrRequesitionMapper csrRequesitionMapper;
	
	@Autowired
	CsrCostcenterMapper costcenterMapper;

	@Override
	public ResultMsg listCostcenter() {
		return ResultMsg.success("病房").addData(costcenterMapper.listCostcenter());
	}


}
