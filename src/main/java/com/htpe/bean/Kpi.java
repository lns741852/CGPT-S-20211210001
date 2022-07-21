package com.htpe.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Kpi {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date startTime;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date endTime;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	

}
