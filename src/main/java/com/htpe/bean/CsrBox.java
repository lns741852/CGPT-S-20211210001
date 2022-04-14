package com.htpe.bean;

import java.util.Date;

public class CsrBox {
	
	private Integer id;
	
	private String udi;
	
	private Integer price;

	private String status;
	
    private String datauser;

    private Date datadate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUdi() {
		return udi;
	}

	public void setUdi(String udi) {
		this.udi = udi;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDatauser() {
		return datauser;
	}

	public void setDatauser(String datausername) {
		this.datauser = datauser;
	}

	public Date getDatadate() {
		return datadate;
	}

	public void setDatadate(Date datadate) {
		this.datadate = datadate;
	}
    
    

}
