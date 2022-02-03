package com.htpe.service;

import javax.servlet.http.HttpServletRequest;

import com.htpe.utils.ResultMsg;

public interface MenuService {

	ResultMsg getMenuByUsername(HttpServletRequest request);

}
