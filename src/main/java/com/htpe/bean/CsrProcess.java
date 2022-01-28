package com.htpe.bean;

import java.util.Date;

public class CsrProcess {
    private Integer id;

    private String processno;

    private String processname;

    private Integer day;

    private String isdelete;

    private String datauserno;

    private String datausername;

    private Date datadate;

    private String moduserno;

    private String modusername;

    private Date moddate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcessno() {
        return processno;
    }

    public void setProcessno(String processno) {
        this.processno = processno == null ? null : processno.trim();
    }

    public String getProcessname() {
        return processname;
    }

    public void setProcessname(String processname) {
        this.processname = processname == null ? null : processname.trim();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
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