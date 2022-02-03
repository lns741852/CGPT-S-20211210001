package com.htpe.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.htpe.mapper.nnew.CsrMenuMapper;
import com.htpe.service.MenuService;
import com.htpe.utils.ResultMsg;

@Service
public class MenuServiceImpl implements MenuService {
	
	@Autowired
	CsrMenuMapper csrMenuMapper;

	@Override
	public ResultMsg getMenuByUsername(HttpServletRequest request) {
		String userno = request.getUserPrincipal().getName();
		return ResultMsg.success("Meun").addData(csrMenuMapper.getMenuByUsername(userno));
	}

}
