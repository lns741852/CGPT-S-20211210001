package com.htpe.service;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.htpe.bean.CsrRoom;
import com.htpe.utils.ResultMsg;

public interface RoomService {

	ResultMsg listRoom(Integer pageNum, Integer pageSize, String searchName);

	ResultMsg saveRoom(@Valid CsrRoom csrRoom, HttpServletRequest request);

	ResultMsg getRoomById(Integer id);

	ResultMsg updateRoom(@Valid CsrRoom csrRoom, HttpServletRequest request);

	ResultMsg removeRoom(Integer id);

}
