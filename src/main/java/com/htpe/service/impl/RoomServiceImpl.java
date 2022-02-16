package com.htpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.htpe.mapper.nnew.CsrRoomMapper;
import com.htpe.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	CsrRoomMapper csrRoomMapper;

}
