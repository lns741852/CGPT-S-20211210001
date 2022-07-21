package com.htpe.bean;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;



public class Report {
	

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date startTime;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private Date endTime;
	
	private String setno;
	
	private String userno;
	
	private String depno;
	
	private String depnoask;
	
	private String centerno;
	
	private String status;
	
    private String disinfection;

    private String pot;

    private Integer potnum;
    
    private String type;


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisinfection() {
		return disinfection;
	}

	public void setDisinfection(String disinfection) {
		this.disinfection = disinfection;
	}

	public String getPot() {
		return pot;
	}

	public void setPot(String pot) {
		this.pot = pot;
	}

	public Integer getPotnum() {
		return potnum;
	}

	public void setPotnum(Integer potnum) {
		this.potnum = potnum;
	}

	/**
	 * @return the depno
	 */
	public String getDepno() {
		return depno;
	}

	/**
	 * @param depno the depno to set
	 */
	public void setDepno(String depno) {
		this.depno = depno;
	}

	/**
	 * @return the depnoask
	 */
	public String getDepnoask() {
		return depnoask;
	}

	/**
	 * @param depnoask the depnoask to set
	 */
	public void setDepnoask(String depnoask) {
		this.depnoask = depnoask;
	}

	/**
	 * @return the centerno
	 */
	public String getCenterno() {
		return centerno;
	}

	/**
	 * @param centerno the centerno to set
	 */
	public void setCenterno(String centerno) {
		this.centerno = centerno;
	}

	/**
	 * @return the userno
	 */
	public String getUserno() {
		return userno;
	}

	/**
	 * @param userno the userno to set
	 */
	public void setUserno(String userno) {
		this.userno = userno;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

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

	public String getSetno() {
		return setno;
	}

	public void setSetno(String setno) {
		this.setno = setno;
	}

	
	
}
