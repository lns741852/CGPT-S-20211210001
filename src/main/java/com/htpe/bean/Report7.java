package com.htpe.bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class Report7 {

	@Excel(name = "包盤代號")
	private  String setno;
	@Excel(name = "包盤名稱" ,width = 30)
	private  String setnamech;
	@Excel(name = "未歸還數量" ,width = 20)
	private Integer nrcount =0;
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
		this.setno = setno == null?  null : setno.trim();
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
		this.setnamech = setnamech == null ? null : setnamech.trim();
	}
	/**
	 * @return the nrcount
	 */
	public Integer getNrcount() {
		return nrcount;
	}
	/**
	 * @param nrcount the nrcount to set
	 */
	public void setNrcount(Integer nrcount) {
		this.nrcount = nrcount;
	}
	
	
	

}
