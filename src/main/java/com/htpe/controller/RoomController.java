package com.htpe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;

}
