package com.htpe.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report11 {
	
	@Excel(name = "UDI代號", width = 30)
	private String udi;
	
	@Excel(name = "送修人員", width = 30)
	private String username;
	

	@Excel(name = "送修時間", format="yyyy-MM-dd HH:mm:ss", width = 30)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date updatetime;

	public String getUdi() {
		return udi;
	}

	public void setUdi(String udi) {
		this.udi = udi;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}


	
	

}
