package com.htpe.bean;

import java.util.Date;

public class CsrRoom {
    private Integer id;

    private String depno;

    private String roomno;

    private String roomname;

    private String bedno;

    private String isdelete;

    private String datauserno;

    private String datausername;

    private Date datadate;

    private String moduserno;

    private String modusername;

    private Date moddate;

    private Integer sn;

    private String isallocate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepno() {
        return depno;
    }

    public void setDepno(String depno) {
        this.depno = depno == null ? null : depno.trim();
    }

    public String getRoomno() {
        return roomno;
    }

    public void setRoomno(String roomno) {
        this.roomno = roomno == null ? null : roomno.trim();
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname == null ? null : roomname.trim();
    }

    public String getBedno() {
        return bedno;
    }

    public void setBedno(String bedno) {
        this.bedno = bedno == null ? null : bedno.trim();
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

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getIsallocate() {
        return isallocate;
    }

    public void setIsallocate(String isallocate) {
        this.isallocate = isallocate == null ? null : isallocate.trim();
    }
}