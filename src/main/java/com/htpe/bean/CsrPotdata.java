package com.htpe.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class CsrPotdata {
    private Integer id;

    @NotEmpty(message = "消毒鍋代號不能為空")
    private String disinfection;

    @NotEmpty(message = "消毒鍋名稱不能為空")
    private String potname;

    private String isdelete;

    private String datauserno;

    private String datausername;

    private Date datadate;
    
    private List<CsrProcessPot> csrProcessPot;    

    public List<CsrProcessPot> getCsrProcessPot() {
		return csrProcessPot;
	}

	public void setCsrProcessPot(List<CsrProcessPot> csrProcessPot) {
		this.csrProcessPot = csrProcessPot;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisinfection() {
        return disinfection;
    }

    public void setDisinfection(String disinfection) {
        this.disinfection = disinfection == null ? null : disinfection.trim();
    }

    public String getPotname() {
        return potname;
    }

    public void setPotname(String potname) {
        this.potname = potname == null ? null : potname.trim();
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete == null ? null : isdelete.trim();
    }

    public String getDatauserno() {
        return datauserno;
    }

    public void setDatauserno(String datauserno) {
        this.datauserno = datauserno == null ? null : datauserno.trim();
    }

    public String getDatausername() {
        return datausername;
    }

    public void setDatausername(String datausername) {
        this.datausername = datausername == null ? null : datausername.trim();
    }

    public Date getDatadate() {
        return datadate;
    }

    public void setDatadate(Date datadate) {
        this.datadate = datadate;
    }
}