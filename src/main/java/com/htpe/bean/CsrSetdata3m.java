package com.htpe.bean;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public class CsrSetdata3m implements Serializable {
	
	private static final long serialVersionUID = 1L;
	   
    private Integer id;

    @NotEmpty(message = "盤包代號不能為空")
    @Excel(name="盤包代號" ,width=15)
    private String setno;

    @NotEmpty(message = "盤包中文不能為空")
    @Excel(name="盤包名稱",width=30)
    private String setnamech;

    private String setname;

    private String settype;

    private String isdelete;

    private Integer price;
    
    @Excel(name="CSR")
    private Integer numbaseCsr;
    
    @Excel(name="CSR2")
    private Integer numbaseCsr2;
    
    @Excel(name="AOR")
    private Integer numbaseOr1;
       
    @Excel(name="BOR")
    private Integer numbaseOr2;
     
    @Excel(name="GOR")
    private Integer numbaseOr3;
    
    @Excel(name="WR")
    private Integer numbaseWr1;

    private String spotno;

    private String sprocess;

    private Integer sday;

    private String sprintno;

    private String positionId;

    private String text01;
    
    @Excel(name="總數")
    private Integer total;
      

    public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSetno() {
        return setno;
    }

    public void setSetno(String setno) {
        this.setno = setno == null ? null : setno.trim();
    }

    public String getSetnamech() {
        return setnamech;
    }

    public void setSetnamech(String setnamech) {
        this.setnamech = setnamech == null ? null : setnamech.trim();
    }

    public String getSetname() {
        return setname;
    }

    public void setSetname(String setname) {
        this.setname = setname == null ? null : setname.trim();
    }

    public String getSettype() {
        return settype;
    }

    public void setSettype(String settype) {
        this.settype = settype == null ? null : settype.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getNumbaseCsr() {
        return numbaseCsr;
    }

    public void setNumbaseCsr(Integer numbaseCsr) {
        this.numbaseCsr = numbaseCsr;
    }

    public Integer getNumbaseCsr2() {
        return numbaseCsr2;
    }

    public void setNumbaseCsr2(Integer numbaseCsr2) {
        this.numbaseCsr2 = numbaseCsr2;
    }

    public Integer getNumbaseOr1() {
        return numbaseOr1;
    }

    public void setNumbaseOr1(Integer numbaseOr1) {
        this.numbaseOr1 = numbaseOr1;
    }

    public Integer getNumbaseOr2() {
        return numbaseOr2;
    }

    public void setNumbaseOr2(Integer numbaseOr2) {
        this.numbaseOr2 = numbaseOr2;
    }

    public Integer getNumbaseOr3() {
        return numbaseOr3;
    }

    public void setNumbaseOr3(Integer numbaseOr3) {
        this.numbaseOr3 = numbaseOr3;
    }

    public Integer getNumbaseWr1() {
        return numbaseWr1;
    }

    public void setNumbaseWr1(Integer numbaseWr1) {
        this.numbaseWr1 = numbaseWr1;
    }

    public String getSpotno() {
        return spotno;
    }

    public void setSpotno(String spotno) {
        this.spotno = spotno == null ? null : spotno.trim();
    }

    public String getSprocess() {
        return sprocess;
    }

    public void setSprocess(String sprocess) {
        this.sprocess = sprocess == null ? null : sprocess.trim();
    }

    public Integer getSday() {
        return sday;
    }

    public void setSday(Integer sday) {
        this.sday = sday;
    }

    public String getSprintno() {
        return sprintno;
    }

    public void setSprintno(String sprintno) {
        this.sprintno = sprintno == null ? null : sprintno.trim();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getText01() {
        return text01;
    }

    public void setText01(String text01) {
        this.text01 = text01 == null ? null : text01.trim();
    }
}