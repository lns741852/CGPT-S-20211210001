package com.htpe.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report9{
	
	@Excel(name = "受補輸單位" ,width = 20)
	private String roomno;
	
	@Excel(name = "包盤代號")
	private  String setno;
	@Excel(name = "包盤名稱" ,width = 30)
	private  String setnamech;
	
	@Excel(name = "瑕疵補輸數量" ,width = 20)
	private Integer num =0;
	

	public String getSetno() {
		return setno;
	}

	public void setSetno(String setno) {
		this.setno = setno;
	}

	public String getSetnamech() {
		return setnamech;
	}

	public void setSetnamech(String setnamech) {
		this.setnamech = setnamech;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getRoomno() {
		return roomno;
	}

	public void setRoomno(String roomno) {
		this.roomno = roomno == null ?null: roomno.trim();
	}
	
	
	

}
