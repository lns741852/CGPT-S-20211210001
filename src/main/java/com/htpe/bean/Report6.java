package com.htpe.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report6 {
	
	@Excel(name = "包盤代號")
	private  String setno;
	@Excel(name = "包盤名稱" ,width = 30)
	private  String setnamech;
	@Excel(name = "製作數量" ,width = 20)
	private Integer mknum =0;
	@Excel(name = "歸還數量",width = 20)
	private Integer rnum =0;
	/**
	 * @return the setno
	 */
	public String getSetno() {
		return setno;
	}
	/**
	 * @param setno the setno to set
	 */
	public void setSetno(String setno) {
		this.setno = setno== null ? null : setno.trim();
	}
	/**
	 * @return the setnamech
	 */
	public String getSetnamech() {
		return setnamech;
	}
	/**
	 * @param setnamech the setnamech to set
	 */
	public void setSetnamech(String setnamech) {
		this.setnamech = setnamech== null ? null : setnamech.trim();
	}
	/**
	 * @return the mknum
	 */
	public Integer getMknum() {
		return mknum;
	}
	/**
	 * @param mknum the mknum to set
	 */
	public void setMknum(Integer mknum) {
		this.mknum = mknum;
	}
	/**
	 * @return the rnum
	 */
	public Integer getRnum() {
		return rnum;
	}
	/**
	 * @param rnum the rnum to set
	 */
	public void setRnum(Integer rnum) {
		this.rnum = rnum;
	}
	
	
	

}
