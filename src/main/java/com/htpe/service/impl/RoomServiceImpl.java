package com.htpe.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htpe.bean.CsrProcessPot;
import com.htpe.bean.CsrRoom;
import com.htpe.exception.RequestPeriodException;
import com.htpe.mapper.nnew.CsrRoomMapper;
import com.htpe.service.RoomService;
import com.htpe.utils.JwtUtils;
import com.htpe.utils.ResultMsg;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	CsrRoomMapper csrRoomMapper;

	@Override
	public ResultMsg listRoom(Integer pageNum, Integer pageSize, String searchName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("pageNum", pageNum);
	    paramMap.put("pageSize", pageSize);
	    paramMap.put("searchName", searchName);
	    PageHelper.startPage(pageNum, pageSize);
		List<Map<String, Object>> accountList = csrRoomMapper.listRoom(paramMap);
	    PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(accountList);    
		return  ResultMsg.success("帳號列表").addData(pageInfo);
	}

	@Override
	public ResultMsg saveRoom(@Valid CsrRoom csrRoom, HttpServletRequest request) {
		int count = csrRoomMapper.countRoomByNo(csrRoom.getRoomno());
		if(count >= 1) {	
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		
		Date date = new Date();	
		csrRoom.setDatadate(date);
		csrRoom.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrRoom.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrRoom.setIsdelete("N");
			
		int num = csrRoomMapper.insertRoom(csrRoom);
		if(num < 1) {
			throw new RequestPeriodException(500, "病房新增失敗");
		}
				
		return ResultMsg.success("病房新增成功").addData("");
	}

	@Override
	public ResultMsg getRoomById(Integer id) {
		return ResultMsg.success("病房").addData(csrRoomMapper.getRoomById(id));
	}

	@Override
	public ResultMsg updateRoom(@Valid CsrRoom csrRoom, HttpServletRequest request) {
		int count = csrRoomMapper.countRoomByNo(csrRoom.getRoomno());
		String changeEmpno = csrRoom.getRoomno();
		String empno = csrRoomMapper.getUserNoById(csrRoom.getId());
		if((!changeEmpno.equals(empno)) && count >=1) {					
			return ResultMsg.fail(500,"帳號已使用").addData("");			
		}
		Date date = new Date();	
		csrRoom.setDatadate(date);
		csrRoom.setDatauserno((String)JwtUtils.validateTokenAndGetClaims(request).get("userid"));
		csrRoom.setDatausername((String)JwtUtils.validateTokenAndGetClaims(request).get("username"));
		csrRoom.setIsdelete("N");
		
		int num = csrRoomMapper.updateRoom(csrRoom);
		if(num < 1) {
			throw new RequestPeriodException(500, "病房修改失敗");
		}
		
		return ResultMsg.success("病房修改成功").addData("");
	}

	@Override
	public ResultMsg removeRoom(Integer id) {
		int count = csrRoomMapper.deleteRoom(id);
		if(count< 1) {
			throw new RequestPeriodException(500, "病房刪除失敗");
		}		
		return ResultMsg.success("病房刪除成功").addData("");
	}

	@Override
	public ResultMsg listDepnoForRoom(String depno) {
		return ResultMsg.success("病房").addData(csrRoomMapper.listDepnoForRoom(depno));
	}

}
