package com.htpe.bean;

import java.util.Date;

public class CsrHistory {
    private Integer id;

    private Integer reqId;

    private String barcode;

    private String userno;

    private String username;

    private String dutyno;

    private String dutyname;

    private Date usertime;

    private Integer sn;

    private String islast;

    private String depno;

    private String patientno;

    private String pcaseno;

    private String isused;

    private String action;

    private String location;

    private String depnolast;

    private String potname;

    private Integer potsn;

    private Integer potid;

    private String potno;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getDutyno() {
        return dutyno;
    }

    public void setDutyno(String dutyno) {
        this.dutyno = dutyno == null ? null : dutyno.trim();
    }

    public String getDutyname() {
        return dutyname;
    }

    public void setDutyname(String dutyname) {
        this.dutyname = dutyname == null ? null : dutyname.trim();
    }

    public Date getUsertime() {
        return usertime;
    }

    public void setUsertime(Date usertime) {
        this.usertime = usertime;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getIslast() {
        return islast;
    }

    public void setIslast(String islast) {
        this.islast = islast == null ? null : islast.trim();
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getPatientno() {
        return patientno;
    }

    public void setPatientno(String patientno) {
        this.patientno = patientno == null ? null : patientno.trim();
    }

    public String getPcaseno() {
        return pcaseno;
    }

    public void setPcaseno(String pcaseno) {
        this.pcaseno = pcaseno == null ? null : pcaseno.trim();
    }

    public String getIsused() {
        return isused;
    }

    public void setIsused(String isused) {
        this.isused = isused == null ? null : isused.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getDepnolast() {
        return depnolast;
    }

    public void setDepnolast(String depnolast) {
        this.depnolast = depnolast == null ? null : depnolast.trim();
    }

    public String getPotname() {
        return potname;
    }

    public void setPotname(String potname) {
        this.potname = potname == null ? null : potname.trim();
    }

    public Integer getPotsn() {
        return potsn;
    }

    public void setPotsn(Integer potsn) {
        this.potsn = potsn;
    }

    public Integer getPotid() {
        return potid;
    }

    public void setPotid(Integer potid) {
        this.potid = potid;
    }

    public String getPotno() {
        return potno;
    }

    public void setPotno(String potno) {
        this.potno = potno == null ? null : potno.trim();
    }
}