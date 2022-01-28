package com.htpe.bean;

import java.util.Date;

public class CsrReceivebatch {
    private Integer id;

    private String depno;

    private Date starttime;

    private String startusername;

    private Date endtime;

    private String endusername;

    private Integer sn;

    private String setno;

    private Integer startsum;

    private Integer tagsum;

    private Integer potsum;

    private Integer endsum;

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

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public String getStartusername() {
        return startusername;
    }

    public void setStartusername(String startusername) {
        this.startusername = startusername == null ? null : startusername.trim();
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getEndusername() {
        return endusername;
    }

    public void setEndusername(String endusername) {
        this.endusername = endusername == null ? null : endusername.trim();
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getSetno() {
        return setno;
    }

    public void setSetno(String setno) {
        this.setno = setno == null ? null : setno.trim();
    }

    public Integer getStartsum() {
        return startsum;
    }

    public void setStartsum(Integer startsum) {
        this.startsum = startsum;
    }

    public Integer getTagsum() {
        return tagsum;
    }

    public void setTagsum(Integer tagsum) {
        this.tagsum = tagsum;
    }

    public Integer getPotsum() {
        return potsum;
    }

    public void setPotsum(Integer potsum) {
        this.potsum = potsum;
    }

    public Integer getEndsum() {
        return endsum;
    }

    public void setEndsum(Integer endsum) {
        this.endsum = endsum;
    }
}