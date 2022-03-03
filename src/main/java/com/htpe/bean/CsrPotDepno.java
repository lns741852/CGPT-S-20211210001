package com.htpe.bean;

import javax.validation.constraints.NotEmpty;

public class CsrPotDepno {
    private Integer id;

    private String depno;

    @NotEmpty(message = "代號不能為空")
    private String disinfection;

    @NotEmpty(message = "數量不能為空")
    private Integer potnum;

    @NotEmpty(message = "起始編號不能為空")
    private Integer beginnum;

    @NotEmpty(message = "字首不能為空")
    private String head;

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

    public String getDisinfection() {
        return disinfection;
    }

    public void setDisinfection(String disinfection) {
        this.disinfection = disinfection == null ? null : disinfection.trim();
    }

    public Integer getPotnum() {
        return potnum;
    }

    public void setPotnum(Integer potnum) {
        this.potnum = potnum;
    }

    public Integer getBeginnum() {
        return beginnum;
    }

    public void setBeginnum(Integer beginnum) {
        this.beginnum = beginnum;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }
}