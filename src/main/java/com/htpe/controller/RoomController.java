package com.htpe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.htpe.bean.CsrPotdata;
import com.htpe.bean.CsrRoom;
import com.htpe.exception.RequestPeriodException;
import com.htpe.service.RoomService;
import com.htpe.utils.ResultMsg;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;

	/**
	 * 病房列表查詢
	 */
	@GetMapping("/room")
	public ResultMsg  listRoom(
			@RequestParam(value = "pageno", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pagesize", required = false, defaultValue = "10") Integer pageSize,
			@RequestParam(value="searchName", required = false) String searchName) {
	    return roomService.listRoom(pageNum,pageSize,searchName);	  
	}
	
	
	/**
	 * 新增手術
	 * @throws RequestPeriodException 
	 */
	@PostMapping("/room")
	public ResultMsg saveSurgical(@Valid @RequestBody CsrRoom csrRoom, HttpServletRequest request) throws RequestPeriodException{
		return roomService.saveRoom(csrRoom,request);
	}
	
	/**
	 * 編輯消毒鍋查詢
	 */
	@GetMapping("/room/{id}")
	public ResultMsg getRoomById(@PathVariable Integer id){	
		return roomService.getRoomById(id);
	}
	
	
	/**
	 * 修改消毒鍋
	 * @throws RequestPeriodException 
	 */
	@PutMapping("/room/{id}")
	public ResultMsg updateRoom(@Valid @RequestBody CsrRoom csrRoom,HttpServletRequest request,
			@PathVariable Integer id) throws RequestPeriodException{
		csrRoom.setId(id);
		return roomService.updateRoom(csrRoom,request);
	}
	
	/**
	 * 刪除消毒鍋
	 * @throws RequestPeriodException 
	 */
	@DeleteMapping("/room/{id}")
	public ResultMsg removeRoom(@PathVariable Integer id) throws RequestPeriodException {
		return roomService.removeRoom(id);
	}
	
}
