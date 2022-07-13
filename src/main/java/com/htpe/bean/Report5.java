package com.htpe.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report5 {
	
	@Excel(name = "交易序號",width = 30)
	private String reqno;
	@Excel(name = "成本中心" ,width = 20)
	private  String centername;
	@Excel(name = "申領類型", replace = { "緊急_G","補輸_B","瑕疵補輸_I"})
	private  String allocatetype;
	@Excel(name = "生日", format="yyyy-MM-dd" ,width = 20)
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private  Date datatime;
	@Excel(name = "包盤代號")
	private  String setno;
	@Excel(name = "包盤名稱" ,width = 20)
	private  String setnamech;
	@Excel(name = "請領數" ,width = 10)
	private Integer setcount;
	@Excel(name = "配送數",width = 10)
	private Integer realcount;

	/**
	 * @return the reqno
	 */
	public String getReqno() {
		return reqno;
	}
	/**
	 * @param reqno the reqno to set
	 */
	public void setReqno(String reqno) {
		this.reqno = reqno== null ? null : reqno.trim();
	}
	/**
	 * @return the centername
	 */
	public String getCentername() {
		return centername;
	}
	/**
	 * @param centername the centername to set
	 */
	public void setCentername(String centername) {
		this.centername = centername== null ? null : centername.trim();
	}
	/**
	 * @return the allocatetype
	 */
	public String getAllocatetype() {
		return allocatetype;
	}
	/**
	 * @param allocatetype the allocatetype to set
	 */
	public void setAllocatetype(String allocatetype) {
		this.allocatetype = allocatetype== null ? null : allocatetype.trim();
	}

	/**
	 * @return the datatime
	 */
	public Date getDatatime() {
		return datatime;
	}
	/**
	 * @param datatime the datatime to set
	 */
	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}
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
	 * @return the setcount
	 */
	public Integer getSetcount() {
		return setcount;
	}
	/**
	 * @param setcount the setcount to set
	 */
	public void setSetcount(Integer setcount) {
		this.setcount = setcount;
	}
	/**
	 * @return the realcount
	 */
	public Integer getRealcount() {
		return realcount;
	}
	/**
	 * @param realcount the realcount to set
	 */
	public void setRealcount(Integer realcount) {
		this.realcount = realcount;
	}
	
	

}
