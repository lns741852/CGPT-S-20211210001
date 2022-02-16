package com.htpe.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CsrSurgical {
    private Integer id;

    @NotEmpty(message = "手術代號不能為空")
    @Size(min = 6, max = 6,message = "手術代號長度為6")
    private String surgicalno;

    @NotEmpty(message = "手術名稱代號不能為空")
    private String surgicalname;

    private String surgicalnamech;

    private String isdelete;

    private String datauserno;

    private String datausername;

    private Date datadate;

    private String moduserno;

    private String modusername;

    private Date moddate;
    
    @NotEmpty(message = "個案車代號不能為空")
    private List<CsrSurgicalCasecar> csrSurgicalCasecar;  

    public List<CsrSurgicalCasecar> getCsrSurgicalCasecar() {
		return csrSurgicalCasecar;
	}

	public void setCsrSurgicalCasecar(List<CsrSurgicalCasecar> csrSurgicalCasecar) {
		this.csrSurgicalCasecar = csrSurgicalCasecar;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurgicalno() {
        return surgicalno;
    }

    public void setSurgicalno(String surgicalno) {
        this.surgicalno = surgicalno == null ? null : surgicalno.trim();
    }

    public String getSurgicalname() {
        return surgicalname;
    }

    public void setSurgicalname(String surgicalname) {
        this.surgicalname = surgicalname == null ? null : surgicalname.trim();
    }

    public String getSurgicalnamech() {
        return surgicalnamech;
    }

    public void setSurgicalnamech(String surgicalnamech) {
        this.surgicalnamech = surgicalnamech == null ? null : surgicalnamech.trim();
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

    public String getModuserno() {
        return moduserno;
    }

    public void setModuserno(String moduserno) {
        this.moduserno = moduserno == null ? null : moduserno.trim();
    }

    public String getModusername() {
        return modusername;
    }

    public void setModusername(String modusername) {
        this.modusername = modusername == null ? null : modusername.trim();
    }

    public Date getModdate() {
        return moddate;
    }

    public void setModdate(Date moddate) {
        this.moddate = moddate;
    }
}